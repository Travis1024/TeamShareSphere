package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.travis.team.pojo.dto.UserInsertDTO;
import org.travis.team.pojo.vo.UserSlimVO;

/**
 * @ClassName UserService
 * @Description UserService
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

    User queryUserById(long userId);

    UserSlimVO querySlimUserById(long userId);

    User userRegister(UserInsertDTO userInsertDTO);

    Long checkPassword(String username, String password);
}
