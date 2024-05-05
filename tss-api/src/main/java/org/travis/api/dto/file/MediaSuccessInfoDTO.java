package org.travis.api.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MediaSuccessInfoDTO
 * @Description MediaSuccessInfoDTO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
@Data
public class MediaSuccessInfoDTO implements Serializable {
    /**
     * ID-文件ID
     */
    @Schema(description="ID-文件ID")
    private Long id;

    /**
     * 密钥文件minio路径
     */
    @Schema(description="密钥文件minio路径")
    private String keyObjectName;

    /**
     * m3u8文件minio路径
     */
    @Schema(description="m3u8文件minio路径")
    private String m3u8ObjectName;
}
