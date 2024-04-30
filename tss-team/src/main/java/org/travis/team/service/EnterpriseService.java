package org.travis.team.service;

import org.travis.team.entity.Enterprise;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName EnterpriseService
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface EnterpriseService extends IService<Enterprise>{


    int updateBatch(List<Enterprise> list);

    int updateBatchSelective(List<Enterprise> list);

    int batchInsert(List<Enterprise> list);

    int insertOrUpdate(Enterprise record);

    int insertOrUpdateSelective(Enterprise record);

}
