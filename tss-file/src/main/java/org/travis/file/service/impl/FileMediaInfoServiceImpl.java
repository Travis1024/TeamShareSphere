package org.travis.file.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.stream.StreamUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.common.enums.BizCodeEnum;
import org.travis.common.exceptions.BadRequestException;
import org.travis.common.exceptions.CommonException;
import org.travis.file.entity.FileInfo;
import org.travis.file.entity.FileMediaInfo;
import org.travis.file.mapper.FileMediaInfoMapper;
import java.util.List;
import java.util.Optional;

import org.travis.file.service.FileMediaInfoService;
import org.travis.file.service.MinioService;
import org.travis.file.utils.OnceTokenUtil;

import javax.annotation.Resource;

/**
 * @ClassName FileMediaInfoServiceImpl
 * @Description FileMediaInfoServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
@Service
public class FileMediaInfoServiceImpl extends ServiceImpl<FileMediaInfoMapper, FileMediaInfo> implements FileMediaInfoService{
    @Resource
    private MinioService minioService;
    @Resource
    private OnceTokenUtil onceTokenUtil;

    @Value("${tss-file.gatewayAddr}")
    private String gatewayAddr;
    @Value("${tss-file.minioAddr}")
    private String minioAddr;

    @Override
    public int updateBatch(List<FileMediaInfo> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<FileMediaInfo> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<FileMediaInfo> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(FileMediaInfo record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(FileMediaInfo record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public ByteArrayInputStream getM3u8File(Long fileId) {
        // 1、查询音视频文件相关信息
        Optional<FileMediaInfo> fileMediaInfoOptional = Optional.ofNullable(getById(fileId));
        if (fileMediaInfoOptional.isEmpty()) {
            throw new BadRequestException("未找到相关文件!");
        }

        // 2、minio 下载 M3U8 文件
        FileMediaInfo fileMediaInfo = fileMediaInfoOptional.get();
        InputStream inputStream = minioService.minioDownload(fileMediaInfo.getEnterpriseId().toString(), fileMediaInfo.getM3u8ObjectName());

        // 3、修改密钥获取链接，修改 ts 播放链接
        String m3u8Content = IoUtil.readUtf8(inputStream);
        m3u8Content = m3u8Content.replace("#{gatewayAddr}", gatewayAddr);
        m3u8Content = m3u8Content.replace("#{fileId}", fileId.toString());
        m3u8Content = m3u8Content.replace("#{token}", onceTokenUtil.getOnceToken());
        m3u8Content = m3u8Content.replace("#{minioAddr}", minioAddr);
        m3u8Content = m3u8Content.replace("#{minioFolder}", fileMediaInfo.getEnterpriseId() + File.pathSeparator + fileId);

        return new ByteArrayInputStream(m3u8Content.getBytes());
    }

    @Override
    public InputStream getKeyFile(Long fileId, String token) {
        // 1、校验 token
        if (!onceTokenUtil.verifyToken(token)) {
            throw new CommonException(BizCodeEnum.MEDIA_TOKEN_CHECK_FAILED.getCode(), BizCodeEnum.MINIO_OPERATION_FAILED.getMessage());
        }

        // 2、查询音视频文件相关信息
        Optional<FileMediaInfo> fileMediaInfoOptional = Optional.ofNullable(getById(fileId));
        if (fileMediaInfoOptional.isEmpty()) {
            throw new BadRequestException("未找到相关文件!");
        }

        // 3、minio 下载 key 文件
        FileMediaInfo fileMediaInfo = fileMediaInfoOptional.get();
        return minioService.minioDownload(fileMediaInfo.getEnterpriseId().toString(), fileMediaInfo.getKeyObjectName());
    }
}
