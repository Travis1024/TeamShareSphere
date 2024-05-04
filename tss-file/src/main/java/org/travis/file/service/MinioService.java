package org.travis.file.service;

/**
 * @ClassName MinioService
 * @Description MinioService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface MinioService {

    void makeBucket(String bucketName);
}
