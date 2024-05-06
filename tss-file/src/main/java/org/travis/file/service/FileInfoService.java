package org.travis.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.travis.file.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.travis.file.pojo.dto.SingleFileUploadDTO;

/**
 * @ClassName FileInfoService
 * @Description FileInfoService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface FileInfoService extends IService<FileInfo>{


    int updateBatch(List<FileInfo> list);

    int updateBatchSelective(List<FileInfo> list);

    int batchInsert(List<FileInfo> list);

    int insertOrUpdate(FileInfo record);

    int insertOrUpdateSelective(FileInfo record);

    void uploadSingleFile(MultipartFile multipartFile, SingleFileUploadDTO singleFileUploadDTO) throws IOException;
}
