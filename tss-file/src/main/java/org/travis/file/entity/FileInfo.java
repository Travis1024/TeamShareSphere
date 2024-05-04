package org.travis.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName FileInfo
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Schema
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file_file_info")
public class FileInfo extends com.baomidou.mybatisplus.extension.activerecord.Model<FileInfo> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID")
    private Long id;

    /**
     * 文件原始名称
     */
    @TableField(value = "`name`")
    @Schema(description="文件原始名称")
    private String name;

    /**
     * 文件描述信息
     */
    @TableField(value = "description")
    @Schema(description="文件描述信息")
    private String description;

    /**
     * 文件下载链接
     */
    @TableField(value = "download_url")
    @Schema(description="文件下载链接")
    private String downloadUrl;

    /**
     * 文件预览链接
     */
    @TableField(value = "preview_url")
    @Schema(description="文件预览链接")
    private String previewUrl;

    /**
     * 文件大小(字节)
     */
    @TableField(value = "`size`")
    @Schema(description="文件大小(字节)")
    private Long size;

    /**
     * 文件 MD5
     */
    @TableField(value = "md5")
    @Schema(description="文件 MD5")
    private String md5;

    /**
     * 文件开放属性（0-私有、1-开放）
     */
    @TableField(value = "property")
    @Schema(description="文件开放属性（0-私有、1-开放）")
    private Integer property;

    /**
     * 文件类型-MultipartFile-ContentType
     */
    @TableField(value = "content_type")
    @Schema(description="文件类型-MultipartFile-ContentType")
    private String contentType;

    /**
     * 文件类型后缀（大写）
     */
    @TableField(value = "suffix")
    @Schema(description="文件类型后缀（大写）")
    private String suffix;

    /**
     * 文件状态（0-文件异常、1-上传中、2-预览文件生成中、3-预览就绪）
     */
    @TableField(value = "`state`")
    @Schema(description="文件状态（0-文件异常、1-上传中、2-预览文件生成中、3-预览就绪）")
    private Integer state;

    /**
     * 文件所属层级（0-企业、1-部门、2-团队、3-个人）
     */
    @TableField(value = "layer")
    @Schema(description="文件所属层级（0-企业、1-部门、2-团队、3-个人）")
    private Integer layer;

    /**
     * 文件所属层级-ID
     */
    @TableField(value = "layer_id")
    @Schema(description="文件所属层级-ID")
    private Long layerId;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    @Schema(description="逻辑删除")
    private Integer isDeleted;

    /**
     * 更新者
     */
    @TableField(value = "updater")
    @Schema(description="更新者")
    private Long updater;

    /**
     * 创建者
     */
    @TableField(value = "creator")
    @Schema(description="创建者")
    private Long creator;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @Schema(description="更新时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_DOWNLOAD_URL = "download_url";

    public static final String COL_PREVIEW_URL = "preview_url";

    public static final String COL_SIZE = "size";

    public static final String COL_MD5 = "md5";

    public static final String COL_PROPERTY = "property";

    public static final String COL_CONTENT_TYPE = "content_type";

    public static final String COL_SUFFIX = "suffix";

    public static final String COL_STATE = "state";

    public static final String COL_LAYER = "layer";

    public static final String COL_LAYER_ID = "layer_id";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
