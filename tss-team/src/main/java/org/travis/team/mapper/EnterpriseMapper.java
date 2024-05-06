package org.travis.team.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.travis.team.entity.Enterprise;

/**
 * @ClassName EnterpriseMapper
 * @Description EnterpriseMapper
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Mapper
public interface EnterpriseMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<Enterprise> {
    int updateBatch(List<Enterprise> list);

    int updateBatchSelective(List<Enterprise> list);

    int batchInsert(@Param("list") List<Enterprise> list);

    int insertOrUpdate(Enterprise record);

    int insertOrUpdateSelective(Enterprise record);
}
