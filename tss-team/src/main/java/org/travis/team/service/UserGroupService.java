package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.UserGroup;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName UserGroupService
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface UserGroupService extends IService<UserGroup>{


    int updateBatch(List<UserGroup> list);

    int updateBatchSelective(List<UserGroup> list);

    int batchInsert(List<UserGroup> list);

    int insertOrUpdate(UserGroup record);

    int insertOrUpdateSelective(UserGroup record);

}
