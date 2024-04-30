package org.travis.team.entity;

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
 * @ClassName Department
 * @Description 部门表
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Schema(description = "部门表")
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tss_team.team_department")
public class Department extends com.baomidou.mybatisplus.extension.activerecord.Model<Department> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID")
    private Long id;

    /**
     * 部门名称
     */
    @TableField(value = "`name`")
    @Schema(description="部门名称")
    private String name;

    /**
     * 部门简述
     */
    @TableField(value = "description")
    @Schema(description="部门简述")
    private String description;

    /**
     * 部门管理者ID
     */
    @TableField(value = "manager_id")
    @Schema(description="部门管理者ID")
    private Long managerId;

    /**
     * 部门管理者名字
     */
    @TableField(value = "manager_name")
    @Schema(description="部门管理者名字")
    private String managerName;

    /**
     * 部门管理者手机号码
     */
    @TableField(value = "manager_phone")
    @Schema(description="部门管理者手机号码")
    private String managerPhone;

    /**
     * 部门管理者邮箱
     */
    @TableField(value = "manager_email")
    @Schema(description="部门管理者邮箱")
    private String managerEmail;

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

    public static final String COL_NAME = "name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_MANAGER_ID = "manager_id";

    public static final String COL_MANAGER_NAME = "manager_name";

    public static final String COL_MANAGER_PHONE = "manager_phone";

    public static final String COL_MANAGER_EMAIL = "manager_email";

    public static final String COL_ENTERPRISE_ID = "enterprise_id";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
