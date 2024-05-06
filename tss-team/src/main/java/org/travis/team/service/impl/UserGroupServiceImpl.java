package org.travis.team.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.team.entity.UserGroup;
import org.travis.team.mapper.UserGroupMapper;
import org.travis.team.service.UserGroupService;
/**
 * @ClassName UserGroupServiceImpl
 * @Description UserGroupServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService{

    @Override
    public int updateBatch(List<UserGroup> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<UserGroup> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<UserGroup> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(UserGroup record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(UserGroup record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
