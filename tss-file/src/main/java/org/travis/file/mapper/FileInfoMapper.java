package org.travis.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.file.entity.FileInfo;

/**
 * @ClassName FileInfoMapper
 * @Description FileInfoMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Mapper
public interface FileInfoMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<FileInfo> {
    int updateBatch(List<FileInfo> list);

    int updateBatchSelective(List<FileInfo> list);

    int batchInsert(@Param("list") List<FileInfo> list);

    int insertOrUpdate(FileInfo record);

    int insertOrUpdateSelective(FileInfo record);
}
