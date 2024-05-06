package org.travis.file.service;

import org.travis.file.entity.FileMediaInfo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName FileMediaInfoService
 * @Description FileMediaInfoService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
public interface FileMediaInfoService extends IService<FileMediaInfo>{
    int updateBatch(List<FileMediaInfo> list);

    int updateBatchSelective(List<FileMediaInfo> list);

    int batchInsert(List<FileMediaInfo> list);

    int insertOrUpdate(FileMediaInfo record);

    int insertOrUpdateSelective(FileMediaInfo record);

    ByteArrayInputStream getM3u8File(Long fileId);

    InputStream getKeyFile(Long fileId, String token);
}
