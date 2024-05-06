package org.travis.api.client.file;

/**
 * @ClassName FileMinioClient
 * @Description FileMinioClient
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface FileMinioClient {

    void makeMinioBucket(String bucketName);

}
