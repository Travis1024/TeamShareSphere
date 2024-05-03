package org.travis.team.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.common.exceptions.DatabaseOperationException;
import org.travis.team.mapper.UserMapper;
import org.travis.team.entity.User;
import org.travis.team.pojo.vo.UserSlimVO;
import org.travis.team.service.UserService;
/**
 * @ClassName UserServiceImpl
 * @Description TODO
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
}
