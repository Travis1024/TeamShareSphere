package org.travis.file.service.impl;

import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.stereotype.Service;
import org.travis.common.exceptions.MinioOperationException;
import org.travis.file.service.MinioService;

import javax.annotation.Resource;

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
                    MakeBucketArgs
                            .builder()
                            .bucket(bucketName)
                            .build()
            );
        } catch (Exception e) {
            throw new MinioOperationException(e.getMessage());
        }
    }
}
