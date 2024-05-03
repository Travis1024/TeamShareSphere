package org.travis.team.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.travis.common.exceptions.BadRequestException;
import org.travis.common.exceptions.DatabaseOperationException;
import org.travis.team.entity.Enterprise;
import org.travis.team.entity.UserEnterprise;
import org.travis.team.mapper.EnterpriseMapper;
import org.travis.team.pojo.dto.EnterpriseInsertDTO;
import org.travis.team.pojo.vo.UserSlimVO;
import org.travis.team.service.EnterpriseService;
import org.travis.team.service.UserEnterpriseService;
import org.travis.team.service.UserService;

import javax.annotation.Resource;

/**
 * @ClassName EnterpriseServiceImpl
 * @Description EnterpriseServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService{
    @Resource
    private UserService userService;
    @Resource
    private UserEnterpriseService userEnterpriseService;

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

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public void insertOne(EnterpriseInsertDTO enterpriseInsertDTO) {
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(enterpriseInsertDTO, enterprise);
        enterprise.setId(IdWorker.getId());

        // 设置当前企业创建用户为企业管理者
        long userId = StpUtil.getLoginIdAsLong();
        enterprise.setManagerId(userId);

        // 查询当前登录用户-基本信息
        UserSlimVO userSlimVO = userService.querySlimUserById(userId);
        if (ObjectUtil.isNull(userSlimVO)) {
            throw new BadRequestException("未查询到当前登录用户信息!");
        }

        // 填充基本信息
        enterprise.setManagerName(userSlimVO.getRealName());
        Optional.ofNullable(enterprise.getManagerPhone()).ifPresentOrElse(null, () -> enterprise.setManagerPhone(userSlimVO.getPhone()));
        Optional.ofNullable(enterprise.getManagerEmail()).ifPresentOrElse(null, () -> enterprise.setManagerEmail(userSlimVO.getEmail()));

        // 初始关联关系信息
        UserEnterprise userEnterprise = new UserEnterprise();
        userEnterprise.setId(IdWorker.getId());
        userEnterprise.setRole(1);
        userEnterprise.setUserId(userId);
        userEnterprise.setEnterpriseId(enterprise.getId());

        // 新增企业信息 + 新增创建者与企业的关联关系
        try {
            getBaseMapper().insert(enterprise);
            userEnterpriseService.getBaseMapper().insert(userEnterprise);
        } catch (RuntimeException re) {
            throw new DatabaseOperationException(re.getMessage());
        }
    }
}
