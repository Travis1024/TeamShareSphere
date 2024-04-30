package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName UserService
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface UserService extends IService<User>{


    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(List<User> list);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

}