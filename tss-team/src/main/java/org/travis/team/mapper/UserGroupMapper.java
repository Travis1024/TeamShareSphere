package org.travis.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.team.entity.UserGroup;

/**
 * @ClassName UserGroupMapper
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Mapper
public interface UserGroupMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<UserGroup> {
    int updateBatch(List<UserGroup> list);

    int updateBatchSelective(List<UserGroup> list);

    int batchInsert(@Param("list") List<UserGroup> list);

    int insertOrUpdate(UserGroup record);

    int insertOrUpdateSelective(UserGroup record);
}