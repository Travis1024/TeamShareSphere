package org.travis.team.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.team.mapper.DepartmentMapper;
import org.travis.team.entity.Department;
import org.travis.team.service.DepartmentService;
/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService{

    @Override
    public int updateBatch(List<Department> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Department> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Department> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(Department record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(Department record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
