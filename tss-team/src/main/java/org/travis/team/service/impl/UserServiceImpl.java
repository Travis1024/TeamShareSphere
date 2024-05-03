package org.travis.team.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.common.exceptions.BadRequestException;
import org.travis.common.exceptions.DatabaseOperationException;
import org.travis.team.mapper.UserMapper;
import org.travis.team.entity.User;
import org.travis.team.pojo.dto.UserInsertDTO;
import org.travis.team.pojo.vo.UserSlimVO;
import org.travis.team.service.UserService;
/**
 * @ClassName UserServiceImpl
 * @Description UserServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public int updateBatch(List<User> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<User> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<User> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(User record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(User record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public User queryUserById(long userId) {
        try {
            User user = getById(userId);
            if (user != null) {
                user.setPassword(null);
            }
            return user;
        } catch (RuntimeException re) {
            throw new DatabaseOperationException(re.getMessage());
        }
    }

    @Override
    public UserSlimVO querySlimUserById(long userId) {
        User user = getOne(
                Wrappers.<User>lambdaQuery()
                        .select(User::getId, User::getUsername, User::getEmail, User::getPhone, User::getRealName, User::getIcon)
                        .eq(User::getId, userId)
        );
        UserSlimVO userSlimVO = new UserSlimVO();
        if (ObjectUtil.isNotNull(user)) {
            BeanUtils.copyProperties(user, userSlimVO);
        }
        return userSlimVO;
    }

    @Override
    public User userRegister(UserInsertDTO userInsertDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInsertDTO, user);
        // 设置用户雪花ID
        user.setId(IdWorker.getId());
        // 用户密码加密
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));

        // 用户信息注册
        try {
            getBaseMapper().insert(user);
        } catch (RuntimeException re) {
            throw new DatabaseOperationException(re.getMessage());
        }
        user.setPassword(null);
        return user;
    }

    @Override
    public Long checkPassword(String username, String password) {
        Optional<User> userOptional = Optional.ofNullable(getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username).select(User::getPassword, User::getId)));
        if (userOptional.isEmpty()) {
            throw new BadRequestException("请检查用户名或密码是否正确!");
        }
        if (!BCrypt.checkpw(password, userOptional.get().getPassword())) {
            throw new BadRequestException("请检查用户名或密码是否正确!");
        }
        return userOptional.get().getId();
    }
}
