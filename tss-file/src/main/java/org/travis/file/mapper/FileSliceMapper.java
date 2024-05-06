package org.travis.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.file.entity.FileSlice;

/**
 * @ClassName FileSliceMapper
 * @Description FileSliceMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Mapper
public interface FileSliceMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<FileSlice> {
    int updateBatch(List<FileSlice> list);

    int updateBatchSelective(List<FileSlice> list);

    int batchInsert(@Param("list") List<FileSlice> list);

    int insertOrUpdate(FileSlice record);

    int insertOrUpdateSelective(FileSlice record);
}
