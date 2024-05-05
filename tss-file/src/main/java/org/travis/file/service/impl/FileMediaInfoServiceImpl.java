package org.travis.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.travis.file.entity.FileMediaInfo;
import org.travis.file.mapper.FileMediaInfoMapper;
import java.util.List;
import org.travis.file.service.FileMediaInfoService;
/**
 * @ClassName FileMediaInfoServiceImpl
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
@Service
public class FileMediaInfoServiceImpl extends ServiceImpl<FileMediaInfoMapper, FileMediaInfo> implements FileMediaInfoService{

    @Override
    public int updateBatch(List<FileMediaInfo> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<FileMediaInfo> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<FileMediaInfo> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(FileMediaInfo record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(FileMediaInfo record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
