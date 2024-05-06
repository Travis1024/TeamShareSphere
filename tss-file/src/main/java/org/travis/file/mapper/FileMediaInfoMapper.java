package org.travis.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.file.entity.FileMediaInfo;

/**
 * @ClassName FileMediaInfoMapper
 * @Description FileMediaInfoMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
@Mapper
public interface FileMediaInfoMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<FileMediaInfo> {
    int updateBatch(List<FileMediaInfo> list);

    int updateBatchSelective(List<FileMediaInfo> list);

    int batchInsert(@Param("list") List<FileMediaInfo> list);

    int insertOrUpdate(FileMediaInfo record);

    int insertOrUpdateSelective(FileMediaInfo record);
}
