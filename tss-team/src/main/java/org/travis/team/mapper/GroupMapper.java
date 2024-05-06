package org.travis.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.team.entity.Group;

/**
 * @ClassName GroupMapper
 * @Description GroupMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Mapper
public interface GroupMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<Group> {
    int updateBatch(List<Group> list);

    int updateBatchSelective(List<Group> list);

    int batchInsert(@Param("list") List<Group> list);

    int insertOrUpdate(Group record);

    int insertOrUpdateSelective(Group record);
}
