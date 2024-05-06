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
 * @ClassName FileSlice
 * @Description FileSlice
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
@TableName(value = "file_file_slice")
public class FileSlice extends com.baomidou.mybatisplus.extension.activerecord.Model<FileSlice> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID")
    private Long id;

    /**
     * 切片所属文件ID
     */
    @TableField(value = "file_id")
    @Schema(description="切片所属文件ID")
    private Long fileId;

    /**
     * 切片上传ID
     */
    @TableField(value = "minio_upload_id")
    @Schema(description="切片上传ID")
    private String minioUploadId;

    /**
     * 切片上传地址
     */
    @TableField(value = "minio_upload_url")
    @Schema(description="切片上传地址")
    private String minioUploadUrl;

    /**
     * 切片MD5
     */
    @TableField(value = "slice_md5")
    @Schema(description="切片MD5")
    private String sliceMd5;

    /**
     * 切片上传状态（0-上传异常、1-上传中、2-上传完成）
     */
    @TableField(value = "slice_state")
    @Schema(description="切片上传状态（0-上传异常、1-上传中、2-上传完成）")
    private Integer sliceState;

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

    public static final String COL_FILE_ID = "file_id";

    public static final String COL_MINIO_UPLOAD_ID = "minio_upload_id";

    public static final String COL_MINIO_UPLOAD_URL = "minio_upload_url";

    public static final String COL_SLICE_MD5 = "slice_md5";

    public static final String COL_SLICE_STATE = "slice_state";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
