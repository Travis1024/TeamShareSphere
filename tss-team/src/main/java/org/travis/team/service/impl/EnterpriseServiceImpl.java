package org.travis.team.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.team.entity.Enterprise;
import org.travis.team.mapper.EnterpriseMapper;
import org.travis.team.pojo.dto.EnterpriseInsertDTO;
import org.travis.team.service.EnterpriseService;
/**
 * @ClassName EnterpriseServiceImpl
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService{

    @Override
    public int updateBatch(List<Enterprise> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Enterprise> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Enterprise> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(Enterprise record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(Enterprise record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public List<Enterprise> queryAll() {
        return getBaseMapper().selectList(null);
    }

    @Override
    public void insertOne(EnterpriseInsertDTO enterpriseInsertDTO) {
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(enterpriseInsertDTO, enterprise);
        long userId = StpUtil.getLoginIdAsLong();
        enterprise.setManagerId(userId);

        int inserted = insertOrUpdate(enterprise);
        if (inserted != 1) {
            throw new RuntimeException("企业新增失败!");
        }
    }
}
