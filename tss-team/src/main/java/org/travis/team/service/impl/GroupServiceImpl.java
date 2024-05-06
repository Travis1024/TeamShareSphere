package org.travis.team.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.team.mapper.GroupMapper;
import org.travis.team.entity.Group;
import org.travis.team.service.GroupService;
/**
 * @ClassName GroupServiceImpl
 * @Description GroupServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService{

    @Override
    public int updateBatch(List<Group> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<Group> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<Group> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(Group record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(Group record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
