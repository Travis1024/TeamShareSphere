package org.travis.file.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName SingleFileUploadDTO
 * @Description SingleFileUploadDTO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/6
 */
@Data
public class SingleFileUploadDTO implements Serializable {
    /**
     * 文件描述信息
     */
    @Schema(description="文件描述信息")
    private String description;

    /**
     * 文件 MD5
     */
    @Schema(description="文件 MD5")
    private String md5;

    /**
     * 文件开放属性（0-私有、1-开放）
     */
    @Schema(description="文件开放属性（0-私有、1-开放）")
    @NotNull(message = "文件开放属性不能为空!")
    private Integer property;

    /**
     * 文件所属层级（0-企业、1-部门、2-团队、3-个人）
     */
    @Schema(description="文件所属层级（0-企业、1-部门、2-团队、3-个人）")
    @NotNull(message = "文件所属层级不能为空!")
    private Integer layer;

    /**
     * 文件所属层级-ID
     */
    @Schema(description="文件所属层级-ID")
    @NotNull(message = "文件所属层级-ID不能为空!")
    private Long layerId;

    /**
     * 文件所属企业-ID
     */
    @Schema(description="文件所属企业ID")
    @NotNull(message = "文件所属企业-ID不能为空!")
    private Long enterpriseId;
}
