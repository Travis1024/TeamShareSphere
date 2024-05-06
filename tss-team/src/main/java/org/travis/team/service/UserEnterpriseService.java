package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.UserEnterprise;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName UserEnterpriseService
 * @Description UserEnterpriseService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface UserEnterpriseService extends IService<UserEnterprise>{


    int updateBatch(List<UserEnterprise> list);

    int updateBatchSelective(List<UserEnterprise> list);

    int batchInsert(List<UserEnterprise> list);

    int insertOrUpdate(UserEnterprise record);

    int insertOrUpdateSelective(UserEnterprise record);

}
