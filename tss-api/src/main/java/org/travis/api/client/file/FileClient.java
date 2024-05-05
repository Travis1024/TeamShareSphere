package org.travis.api.client.file;

import org.travis.api.dto.file.FileInfoSlimDTO;
import org.travis.api.dto.file.MediaSuccessInfoDTO;

/**
 * @ClassName FileClient
 * @Description FileClient
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface FileClient {

    /**
     * 查询音视频文件基础信息
     * @param fileId 文件 ID
     * @return FileInfoSlimDTO
     */
    FileInfoSlimDTO querySlimInfoById(Long fileId);

    /**
     * 异常消息回调
     *
     * @param fileId       文件ID
     * @param errorMessage 异常消息
     */
    void setErrorInfo(Long fileId, String errorMessage);

    /**
     * 成功消息回调
     * @param fileId    文件ID
     * @param mediaSuccessInfoDTO mediaSuccessInfoDTO
     */
    void setSuccessInfo(Long fileId, MediaSuccessInfoDTO mediaSuccessInfoDTO);

}
