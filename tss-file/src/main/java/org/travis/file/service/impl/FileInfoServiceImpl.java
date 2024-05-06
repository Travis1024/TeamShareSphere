package org.travis.file.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Optional;

import org.travis.common.exceptions.BadRequestException;
import org.travis.file.mapper.FileInfoMapper;
import org.travis.file.entity.FileInfo;
import org.travis.file.service.FileInfoService;
import org.travis.file.service.MinioService;

import javax.annotation.Resource;

/**
 * @ClassName FileInfoServiceImpl
 * @Description FileInfoServiceImpl
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService{
    @Resource
    private MinioService minioService;

    @Override
    public int updateBatch(List<FileInfo> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<FileInfo> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<FileInfo> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(FileInfo record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(FileInfo record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

}
