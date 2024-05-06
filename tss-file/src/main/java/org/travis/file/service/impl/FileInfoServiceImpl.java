package org.travis.file.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.travis.api.dto.file.FileInfoSlimDTO;
import org.travis.common.constants.RocketMqConstant;
import org.travis.file.mapper.FileInfoMapper;
import org.travis.file.entity.FileInfo;
import org.travis.file.pojo.dto.SingleFileUploadDTO;
import org.travis.file.service.FileInfoService;
import org.travis.file.service.MinioService;

import javax.annotation.Resource;

/**
 * @ClassName FileInfoServiceImpl
 * @Description FileInfoServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Slf4j
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService{
    @Resource
    private MinioService minioService;
    @Resource
    private RocketMQTemplate rocketMqTemplate;

    @Override
    public int updateBatch(List<FileInfo> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<FileInfo> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<FileInfo> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(FileInfo record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(FileInfo record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Transactional
    @Override
    public void uploadSingleFile(MultipartFile multipartFile, SingleFileUploadDTO singleFileUploadDTO) throws IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(IdWorker.getId());
        BeanUtils.copyProperties(singleFileUploadDTO, fileInfo);

        // 如果未计算文件 MD5，则进行计算
        if (StrUtil.isEmpty(fileInfo.getMd5())) {
            fileInfo.setMd5(calcFileMd5(multipartFile.getInputStream()));
        }

        // 获取并校验文件名称
        String filename = multipartFile.getOriginalFilename();
        if (StrUtil.isEmpty(filename)) {
            throw new BadRequestException("文件校验失败!");
        }

        // 获取文件后缀
        int index = filename.lastIndexOf(".");
        String fileSuffix = index == -1 ? null : filename.substring(index + 1).toUpperCase();

        fileInfo.setName(filename);
        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setContentType(multipartFile.getContentType());
        fileInfo.setState(1);
        fileInfo.setSuffix(fileSuffix);

        // 新增文件信息
        save(fileInfo);

        // 上传文件到 minio 中
        minioService.uploadSingleFile(multipartFile.getInputStream(), fileInfo.getEnterpriseId().toString(), fileInfo.getName(), fileInfo.getContentType());

        // 修改文件状态信息
        update(Wrappers.<FileInfo>lambdaUpdate().set(FileInfo::getState, 2).eq(FileInfo::getId, fileInfo.getId()));

        // 判断文件类型并发送 MQ 消息
        if ("MP4".equals(fileSuffix)) {
            FileInfoSlimDTO fileInfoSlimDTO = new FileInfoSlimDTO();
            BeanUtils.copyProperties(fileInfo, fileInfoSlimDTO);
            rocketMqTemplate.asyncSend(
                    RocketMqConstant.TOPIC_MEDIA_NAME,
                    MessageBuilder.withPayload(fileInfoSlimDTO).build(),
                    new SendCallback() {
                        @Override
                        public void onSuccess(SendResult sendResult) {
                            log.info("Media-MQ 消息发送成功：{}", fileInfo.getId());
                        }
                        @Override
                        public void onException(Throwable throwable) {
                            log.error("Media-MQ 消息发送失败：{}, 原因：{}", fileInfo.getId(), throwable.getMessage());
                        }
                    }
            );
        }
    }


    public static String calcFileMd5(InputStream inputStream) {
        byte[] bytes = IoUtil.readBytes(inputStream);
        return DigestUtil.md5Hex(bytes);
    }

}
