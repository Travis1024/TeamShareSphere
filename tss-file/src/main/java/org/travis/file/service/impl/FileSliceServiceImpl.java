package org.travis.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.travis.file.mapper.FileSliceMapper;
import org.travis.file.entity.FileSlice;
import org.travis.file.service.FileSliceService;
/**
 * @ClassName FileSliceServiceImpl
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Service
public class FileSliceServiceImpl extends ServiceImpl<FileSliceMapper, FileSlice> implements FileSliceService{

    @Override
    public int updateBatch(List<FileSlice> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<FileSlice> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<FileSlice> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(FileSlice record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(FileSlice record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
