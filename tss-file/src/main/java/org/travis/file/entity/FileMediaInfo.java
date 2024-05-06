package org.travis.file.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * @ClassName FileMediaInfo
 * @Description FileMediaInfo
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
@Schema
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file_file_media_info")
public class FileMediaInfo extends com.baomidou.mybatisplus.extension.activerecord.Model<FileMediaInfo> implements Serializable {
    /**
     * ID-文件ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID-文件ID")
    private Long id;

    /**
     * 密钥文件minio路径
     */
    @TableField(value = "key_object_name")
    @Schema(description="密钥文件minio路径")
    private String keyObjectName;

    /**
     * m3u8文件minio路径
     */
    @TableField(value = "m3u8_object_name")
    @Schema(description="m3u8文件minio路径")
    private String m3u8ObjectName;

    /**
     * 所属企业ID
     */
    @TableField(value = "enterprise_id")
    @Schema(description="所属企业ID")
    private Long enterpriseId;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    @Schema(description="逻辑删除")
    @TableLogic
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

    public static final String COL_KEY_OBJECT_NAME = "key_object_name";

    public static final String COL_M3U8_OBJECT_NAME = "m3u8_object_name";

    public static final String COL_ENTERPRISE_ID = "enterprise_id";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
