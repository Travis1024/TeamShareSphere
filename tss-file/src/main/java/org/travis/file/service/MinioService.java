package org.travis.file.service;

import java.io.InputStream;

/**
 * @ClassName MinioService
 * @Description MinioService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface MinioService {

    void makeBucket(String bucketName);

    InputStream minioDownload(String bucketName, String objectName);

    void uploadSingleFile(InputStream inputStream, String bucketName, String objectName, String contentType);
}
