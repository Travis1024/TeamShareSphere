package org.travis.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.team.entity.Department;

/**
 * @ClassName DepartmentMapper
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Mapper
public interface DepartmentMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<Department> {
    int updateBatch(List<Department> list);

    int updateBatchSelective(List<Department> list);

    int batchInsert(@Param("list") List<Department> list);

    int insertOrUpdate(Department record);

    int insertOrUpdateSelective(Department record);
}