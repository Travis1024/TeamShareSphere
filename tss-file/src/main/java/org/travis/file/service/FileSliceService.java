package org.travis.file.service;

import java.util.List;
import org.travis.file.entity.FileSlice;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName FileSliceService
 * @Description FileSliceService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface FileSliceService extends IService<FileSlice>{


    int updateBatch(List<FileSlice> list);

    int updateBatchSelective(List<FileSlice> list);

    int batchInsert(List<FileSlice> list);

    int insertOrUpdate(FileSlice record);

    int insertOrUpdateSelective(FileSlice record);

}
