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
 * @ClassName Group
 * @Description 团队信息表
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Schema(description = "团队信息表")
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tss_team.team_group")
public class Group extends com.baomidou.mybatisplus.extension.activerecord.Model<Group> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID")
    private Long id;

    /**
     * 团队名称
     */
    @TableField(value = "`name`")
    @Schema(description="团队名称")
    private String name;

    /**
     * 团队简述
     */
    @TableField(value = "description")
    @Schema(description="团队简述")
    private String description;

    /**
     * 团队管理者ID
     */
    @TableField(value = "manager_id")
    @Schema(description="团队管理者ID")
    private Long managerId;

    /**
     * 团队管理者名字
     */
    @TableField(value = "manager_name")
    @Schema(description="团队管理者名字")
    private String managerName;

    /**
     * 团队管理者手机号码
     */
    @TableField(value = "manager_phone")
    @Schema(description="团队管理者手机号码")
    private String managerPhone;

    /**
     * 团队管理者邮箱
     */
    @TableField(value = "manager_email")
    @Schema(description="团队管理者邮箱")
    private String managerEmail;

    /**
     * 团队类型（0-部门内部、1-外部）
     */
    @TableField(value = "`type`")
    @Schema(description="团队类型（0-部门内部、1-外部）")
    private Integer type;

    /**
     * 团队所属企业ID
     */
    @TableField(value = "enterprise_id")
    @Schema(description="团队所属企业ID")
    private Long enterpriseId;

    /**
     * 团队所属部门ID（内部必填、外部为空）
     */
    @TableField(value = "department_id")
    @Schema(description="团队所属部门ID（内部必填、外部为空）")
    private Long departmentId;

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

    public static final String COL_TYPE = "type";

    public static final String COL_ENTERPRISE_ID = "enterprise_id";

    public static final String COL_DEPARTMENT_ID = "department_id";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
