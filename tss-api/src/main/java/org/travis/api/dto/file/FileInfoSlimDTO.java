package org.travis.api.dto.file;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FileInfoSlimDTO
 * @Description FileInfoSlimDTO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Data
public class FileInfoSlimDTO implements Serializable {
    /**
     * 文件所属企业ID
     */
    @Schema(description = "文件所属企业ID")
    private Long enterpriseId;

    /**
     * 文件ID
     */
    @Schema(description="ID")
    private Long id;

    /**
     * 文件原始名称
     */
    @Schema(description="文件原始名称")
    private String name;

    /**
     * 文件类型后缀（大写）
     */
    @Schema(description="文件类型后缀（大写）")
    private String suffix;
}
