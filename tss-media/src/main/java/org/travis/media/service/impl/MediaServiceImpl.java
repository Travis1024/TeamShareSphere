package org.travis.media.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.ContentType;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.travis.api.client.file.FileClient;
import org.travis.api.dto.file.FileInfoSlimDTO;
import org.travis.api.dto.file.MediaSuccessInfoDTO;
import org.travis.common.exceptions.MinioOperationException;
import org.travis.media.constants.MediaConstant;
import org.travis.media.service.MediaService;
import org.travis.media.service.MinioService;
import ws.schild.jave.process.ProcessWrapper;
import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * @ClassName MediaServiceImpl
 * @Description MediaServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Slf4j
@Service
public class MediaServiceImpl implements MediaService {

    @DubboReference
    private FileClient fileClient;
    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioService minioService;
    @Resource
    private MediaService mediaService;

    @Override
    public void handlerMedia(Long fileId) {
        try {
            // 1、查询视频文件基础信息
            FileInfoSlimDTO fileInfoSlimDTO = fileClient.querySlimInfoById(fileId);

            // 2、初始化视频文件存放地址
            String tempFileFolder = File.pathSeparator + "tmp" + File.pathSeparator + "tss" + File.pathSeparator + fileId + File.pathSeparator;
            String tempFilePath = tempFileFolder + MediaConstant.MEDIA_FILE_MIDDLE_NAME + "." + fileInfoSlimDTO.getSuffix();

            if (FileUtil.exist(tempFilePath)) {
                FileUtil.del(tempFilePath);
            }
            File file = FileUtil.touch(tempFilePath);

            // 3、从 minio 下载视频文件
            try {
                FileUtil.writeFromStream(
                        minioClient.getObject(
                                GetObjectArgs.builder()
                                        .bucket(fileInfoSlimDTO.getEnterpriseId().toString())
                                        .object(fileId + "." + fileInfoSlimDTO.getSuffix())
                                        .build()
                        ),
                        file
                );
            } catch (IOException ioException) {
                log.error("[{}] -> 视频下载失败!", fileId);
                throw new MinioOperationException("视频下载失败: " + fileId);
            }

            // 4、创建加密秘钥，上传秘钥到 Minio，存储相关字段，生成 keyinfo，视频切片及加密，删除本地缓存
            MediaSuccessInfoDTO mediaSuccessInfoDTO = mediaService.handlerKms(fileInfoSlimDTO, tempFileFolder);

            // 5、发送视频处理成功回调
            log.error("[视频处理成功: {}] -> {}", fileId, "Success!");
            fileClient.setSuccessInfo(fileId, mediaSuccessInfoDTO);
        } catch (Exception e) {
            log.error("[视频处理失败: {}] -> {}", fileId, e.getMessage());
            fileClient.setErrorInfo(fileId, e.getMessage());
        }
    }

    @Override
    public MediaSuccessInfoDTO handlerKms(FileInfoSlimDTO fileInfoSlimDTO, String tempFileFolder) throws IOException {
        // 1、生成 16 字节的随机秘钥
        byte[] keys = RandomUtil.randomBytes(16);

        // 2、创建秘钥文件
        String keyPath = tempFileFolder + MediaConstant.KEY_FILE_NAME;
        File keyFile = FileUtil.touch(keyPath);

        // 3、保存秘钥文件
        FileUtil.writeBytes(keys, keyFile);

        // 4.上传秘钥文件到 Minio  (秘钥文件链接 通过/file/info/getKeyFile?fileId=xxx 获取)
        String keyObjectName = File.pathSeparator + fileInfoSlimDTO.getId() + File.pathSeparator + MediaConstant.KEY_FILE_NAME;
        String bucketName = fileInfoSlimDTO.getEnterpriseId().toString();
        String contentType = ContentType.TEXT_PLAIN.getValue();
        minioService.uploadFile(bucketName, keyObjectName, contentType, keyPath);

        // 5.生成 keyinfo 文件
        String keyInfoPath = tempFileFolder + MediaConstant.KEY_INFO_FILE_NAME;
        File keyinfoFile = FileUtil.touch(keyInfoPath);
        // 5.1.生成 keyinfo 文件内容
        byte[] contentBytes = (MediaConstant.KEY_FILE_GET_URL + System.lineSeparator() + keyPath + System.lineSeparator() + IdUtil.simpleUUID()).getBytes();
        // 5.2.将内容写入文件中
        FileUtil.writeBytes(contentBytes, keyinfoFile);

        // 6.进行视频切片及加密
        // 6.1 判断视频是否是 MP4 格式，不是则转为 MP4
        String middlePath = tempFileFolder + MediaConstant.MEDIA_FILE_MIDDLE_NAME + "." + fileInfoSlimDTO.getSuffix();
        String targetName = MediaConstant.MEDIA_FILE_TARGET_NAME + ".MP4";
        String targetPath = tempFileFolder + targetName;
        if ("mp4".equalsIgnoreCase(fileInfoSlimDTO.getSuffix())) {
            FileUtil.rename(new File(middlePath), targetName, false, true);
        } else {
            formatToMp4(middlePath, targetPath);
        }

        // 6.2 对 MP4 视频进行切片
        String keyinfoPath = tempFileFolder + MediaConstant.KEY_INFO_FILE_NAME;
        String outputPath = tempFileFolder + MediaConstant.M3U8_FILE_NAME;
        execFfmpegSlice(targetPath, keyinfoPath, outputPath);

        // 7.视频切片文件上传
        List<File> loopFiles = FileUtil.loopFiles(tempFileFolder, pathname -> pathname.isFile() && (pathname.getName().endsWith(".ts") || pathname.getName().endsWith(".m3u8")));
        minioService.batchUploadFiles(fileInfoSlimDTO.getEnterpriseId().toString(), fileInfoSlimDTO.getId().toString(), loopFiles, 20);

        // 8.删除本地缓存文件夹（TODO 可以考虑异步）
        FileUtil.del(tempFileFolder);

        // 9.返回回调结果
        MediaSuccessInfoDTO mediaSuccessInfoDTO = new MediaSuccessInfoDTO();
        mediaSuccessInfoDTO.setId(fileInfoSlimDTO.getId());
        mediaSuccessInfoDTO.setKeyObjectName(keyObjectName);
        mediaSuccessInfoDTO.setM3u8ObjectName(File.pathSeparator + fileInfoSlimDTO.getId().toString() + File.pathSeparator + MediaConstant.M3U8_FILE_NAME);
        return mediaSuccessInfoDTO;
    }


    /**
     * @MethodName formatToMp4
     * @Description 视频格式转换为 MP4
     * @Author travis-wei
     * @Data 2024/5/5
     * @param middlePath	中间 video 文件
     * @param targetPath	最终 video 文件
     * @Return void
     **/
    private static void formatToMp4(String middlePath, String targetPath) {
        // 命令示例：ffmpeg -i input.mkv -vcodec copy -acodec copy out.mp4
        try {
            ProcessWrapper ffmpeg = new DefaultFFMPEGLocator().createExecutor();
            ffmpeg.addArgument("-i");
            ffmpeg.addArgument(middlePath);
            ffmpeg.addArgument("-vcodec copy");
            ffmpeg.addArgument("-acodec copy");
            ffmpeg.addArgument(targetPath);
            ffmpeg.execute();
            log.info("[{}] -> 开始执行视频格式转换!", middlePath);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ffmpeg.getErrorStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    log.info(line);
                }
            }
            log.info("[{}] -> 视频格式转换成功!", middlePath);
        } catch (Exception e) {
            log.error("[{}] 视频格式转换失败!", middlePath);
            throw new RuntimeException("视频格式转换失败: " + middlePath);
        }
    }

    /**
     * @MethodName execFfmpegSlice
     * @Description 视频切片方法
     * @Author travis-wei
     * @Data 2024/5/5
     * @param videoPath 源视频路径
     * @param keyinfoPath   keyinfo 路径
     * @param outputPath	输出 m3u8 路径
     * @Return void
     **/
    private static void execFfmpegSlice(String videoPath, String keyinfoPath, String outputPath) {
        // 命令示例：ffmpeg -i input.mp4 -vcodec copy -acodec copy -f hls -hls_time 20 -hls_list_size 0 -hls_base_url "http://#{minioAddr}/#{minioFolder}/"
        // -hls_key_info_file encrypt.keyinfo -hls_playlist_type vod output.m3u8
        try {
            ProcessWrapper ffmpeg = new DefaultFFMPEGLocator().createExecutor();
            // 指定源视频文件
            ffmpeg.addArgument("-i");
            ffmpeg.addArgument(videoPath);
            // 指定视频编解码方式
            ffmpeg.addArgument("-vcodec copy");
            ffmpeg.addArgument("-acodec copy");
            // 设置输入输出的文件格式
            ffmpeg.addArgument("-f hls");
            // 每段文件的时间长度(单位:秒)
            ffmpeg.addArgument("-hls_time 20");
            // 索引播放列表的最大列数 默认5，0 为不限制
            ffmpeg.addArgument("-hls_list_size 0");
            // 表示当前的视频流并不是一个直播流，而是点播流
            ffmpeg.addArgument("-hls_playlist_type vod");
            // 设置 ts 文件的网络前缀路径
            ffmpeg.addArgument("-hls_base_url");
            ffmpeg.addArgument(MediaConstant.TS_URL_PREFIX);
            // 设置 keyinfo 文件路径
            ffmpeg.addArgument("-hls_key_info_file");
            ffmpeg.addArgument(keyinfoPath);
            // 设置 m3u8 输出文件名称
            ffmpeg.addArgument(outputPath);
            ffmpeg.execute();
            log.info("[{}] -> 开始执行视频切片!", videoPath);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ffmpeg.getErrorStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    log.info(line);
                }
            }
            log.info("[{}] -> 视频切片成功!", videoPath);
        } catch (Exception e) {
            log.error("[{}] 视频切片失败!", videoPath);
            throw new RuntimeException("视频切片失败: " + videoPath);
        }
    }
}
