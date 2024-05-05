package org.travis.media.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName MinioService
 * @Description MinioService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
public interface MinioService {
    void uploadFile(String bucketName, String objectName, String contentType, String filePath) throws IOException;

    void batchUploadFiles(String bucketName, String minioFolder, List<File> loopFiles, int groupNumber);
}
