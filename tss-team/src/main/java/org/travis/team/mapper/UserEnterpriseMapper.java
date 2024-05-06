package org.travis.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.team.entity.UserEnterprise;

/**
 * @ClassName UserEnterpriseMapper
 * @Description UserEnterpriseMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Mapper
public interface UserEnterpriseMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<UserEnterprise> {
    int updateBatch(List<UserEnterprise> list);

    int updateBatchSelective(List<UserEnterprise> list);

    int batchInsert(@Param("list") List<UserEnterprise> list);

    int insertOrUpdate(UserEnterprise record);

    int insertOrUpdateSelective(UserEnterprise record);
}
