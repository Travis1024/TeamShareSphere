package org.travis.team.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.team.mapper.UserDepartmentMapper;
import org.travis.team.entity.UserDepartment;
import org.travis.team.service.UserDepartmentService;
/**
 * @ClassName UserDepartmentServiceImpl
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class UserDepartmentServiceImpl extends ServiceImpl<UserDepartmentMapper, UserDepartment> implements UserDepartmentService{

    @Override
    public int updateBatch(List<UserDepartment> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<UserDepartment> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<UserDepartment> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(UserDepartment record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(UserDepartment record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
