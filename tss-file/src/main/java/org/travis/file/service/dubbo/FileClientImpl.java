package org.travis.file.service.dubbo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.travis.api.client.file.FileClient;
import org.travis.api.dto.file.FileInfoSlimDTO;
import org.travis.api.dto.file.MediaSuccessInfoDTO;
import org.travis.file.entity.FileInfo;
import org.travis.file.entity.FileMediaInfo;
import org.travis.file.mapper.FileInfoMapper;
import org.travis.file.mapper.FileMediaInfoMapper;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @ClassName FileClientImpl
 * @Description FileClient Dubbo 服务提供者
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/6
 */
@Slf4j
@DubboService(timeout = 3000)
public class FileClientImpl implements FileClient {
    @Resource
    private FileInfoMapper fileInfoMapper;
    @Resource
    private FileMediaInfoMapper fileMediaInfoMapper;

    @Override
    public FileInfoSlimDTO querySlimInfoById(Long fileId) {
        Optional<FileInfo> fileInfoOptional = Optional.ofNullable(
                fileInfoMapper.selectOne(
                        Wrappers.<FileInfo>lambdaQuery()
                                .select(FileInfo::getId, FileInfo::getEnterpriseId, FileInfo::getSuffix, FileInfo::getSuffix, FileInfo::getName)
                                .eq(FileInfo::getId, fileId)
                )
        );
        FileInfoSlimDTO fileInfoSlimDTO = new FileInfoSlimDTO();
        fileInfoOptional.ifPresent(fileInfo -> BeanUtils.copyProperties(fileInfo, fileInfoSlimDTO));
        return fileInfoOptional.isEmpty() ? null : fileInfoSlimDTO;
    }

    @Override
    public void setErrorInfo(Long fileId, String errorMessage) {
        log.error("[Media Handler Result: {}], Error: {}", fileId, errorMessage);
        fileInfoMapper.update(
                Wrappers.<FileInfo>lambdaUpdate()
                        .set(FileInfo::getState, 1)
                        .set(FileInfo::getStateMessage, errorMessage)
                        .eq(FileInfo::getId, fileId)
        );
    }

    @Override
    public void setSuccessInfo(MediaSuccessInfoDTO mediaSuccessInfoDTO) {
        Long fileId = mediaSuccessInfoDTO.getId();
        log.info("[Media Handler Result: {}], Success!", fileId);
        // 更新文件信息表
        fileInfoMapper.update(Wrappers.<FileInfo>lambdaUpdate().set(FileInfo::getState, 4).eq(FileInfo::getId, fileId));
        // 更新文件音视频信息表
        fileMediaInfoMapper.update(
                Wrappers.<FileMediaInfo>lambdaUpdate()
                        .set(FileMediaInfo::getKeyObjectName, mediaSuccessInfoDTO.getKeyObjectName())
                        .set(FileMediaInfo::getM3u8ObjectName, mediaSuccessInfoDTO.getM3u8ObjectName())
                        .eq(FileMediaInfo::getId, fileId)
        );
    }
}
