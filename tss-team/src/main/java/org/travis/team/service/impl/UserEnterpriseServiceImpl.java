package org.travis.team.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.team.mapper.UserEnterpriseMapper;
import org.travis.team.entity.UserEnterprise;
import org.travis.team.service.UserEnterpriseService;
/**
 * @ClassName UserEnterpriseServiceImpl
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class UserEnterpriseServiceImpl extends ServiceImpl<UserEnterpriseMapper, UserEnterprise> implements UserEnterpriseService{

    @Override
    public int updateBatch(List<UserEnterprise> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<UserEnterprise> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<UserEnterprise> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(UserEnterprise record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(UserEnterprise record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
