package org.travis.file.service;

import org.travis.file.entity.FileMediaInfo;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName FileMediaInfoService
 * @Description TODO
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

}
