package org.travis.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.team.entity.UserDepartment;

/**
 * @ClassName UserDepartmentMapper
 * @Description UserDepartmentMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Mapper
public interface UserDepartmentMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<UserDepartment> {
    int updateBatch(List<UserDepartment> list);

    int updateBatchSelective(List<UserDepartment> list);

    int batchInsert(@Param("list") List<UserDepartment> list);

    int insertOrUpdate(UserDepartment record);

    int insertOrUpdateSelective(UserDepartment record);
}
