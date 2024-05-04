package org.travis.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.travis.file.service.MinioService;

import javax.annotation.Resource;

/**
 * @ClassName MinioOperationController
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Slf4j
@RestController
@RequestMapping("/minio")
public class MinioOperationController {
    @Resource
    private MinioService minioService;

    @Operation(summary = "Minio 创建存储桶")
    @GetMapping("/bucket/make")
    public void makeBucket(@RequestParam("bucketName") String bucketName) {
        minioService.makeBucket(bucketName);
    }

}
