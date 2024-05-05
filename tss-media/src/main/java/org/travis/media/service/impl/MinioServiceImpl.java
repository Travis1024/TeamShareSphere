package org.travis.media.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.ContentType;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SnowballObject;
import io.minio.UploadSnowballObjectsArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.travis.common.exceptions.MinioOperationException;
import org.travis.media.service.MinioService;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MinioServiceImpl
 * @Description MinioServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
@Slf4j
@Service
public class MinioServiceImpl implements MinioService {
    @Resource
    private MinioClient minioClient;

    @Override
    public void uploadFile(String bucketName, String objectName, String contentType, String filePath) throws IOException {
        File file = new File(filePath);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build()
            );
        } catch (Exception e) {
            log.warn("[{}] 文件上传错误, {}", filePath, e.toString());
            throw new MinioOperationException("文件上传错误: " + e.getMessage());
        }
    }

    @Override
    public void batchUploadFiles(String bucketName, String minioFolder, List<File> loopFiles, int groupNumber) {
        // 判断文件列表是否为空
        if (loopFiles == null || loopFiles.isEmpty()) {
            return;
        }
        try {
            // groupNumber 个文件为 one batch
            for (int i = 0; i < (loopFiles.size() / (groupNumber + 1)) + 1; i++) {
                List<SnowballObject> objectList = new ArrayList<>();
                for (int j = groupNumber * i; j < Math.min(groupNumber * (i + 1), loopFiles.size()); j++) {
                    File file = loopFiles.get(j);
                    byte[] bytes = FileUtil.readBytes(file);
                    objectList.add(new SnowballObject(File.pathSeparatorChar + minioFolder + File.pathSeparatorChar + file.getName(), new ByteArrayInputStream(bytes), bytes.length, ZonedDateTime.now()));
                }

                minioClient.uploadSnowballObjects(
                        UploadSnowballObjectsArgs.builder()
                                .bucket(bucketName)
                                .objects(objectList)
                                .build()
                );
            }

        } catch (Exception e) {
            log.warn("文件批量上传错误, {}", e.toString());
            throw new MinioOperationException("文件批量上传错误: " + e.getMessage());
        }
    }
}
