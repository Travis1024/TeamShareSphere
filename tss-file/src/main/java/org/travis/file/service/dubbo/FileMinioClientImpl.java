package org.travis.file.service.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.travis.api.client.file.FileMinioClient;
import org.travis.file.service.MinioService;

import javax.annotation.Resource;

/**
 * @ClassName FileMinioClientImpl
 * @Description FileMinioClient Dubbo 服务提供者
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Slf4j
@DubboService(timeout = 3000)
public class FileMinioClientImpl implements FileMinioClient {
    @Resource
    private MinioService minioService;


    @Override
    public void makeMinioBucket(String bucketName) {
        minioService.makeBucket(bucketName);
    }
}
