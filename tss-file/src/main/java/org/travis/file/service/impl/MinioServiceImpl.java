package org.travis.file.service.impl;

import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.travis.common.exceptions.MinioOperationException;
import org.travis.file.service.MinioService;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @ClassName MinioServiceImpl
 * @Description MinioServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Service
public class MinioServiceImpl implements MinioService {
    @Resource
    private MinioClient minioClient;

    @Override
    public void makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
        } catch (Exception e) {
            throw new MinioOperationException(e.getMessage());
        }
    }

    @Override
    public InputStream minioDownload(String bucketName, String objectName) {
        try {
            return minioClient.getObject(
              GetObjectArgs.builder()
                      .bucket(bucketName)
                      .object(objectName)
                      .build()
            );
        } catch (Exception e) {
            throw new MinioOperationException(e.getMessage());
        }
    }

    @Override
    public void uploadSingleFile(InputStream inputStream, String bucketName, String objectName, String contentType) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build()
            );
        } catch (Exception e) {
            throw new MinioOperationException(e.getMessage());
        }
    }
}
