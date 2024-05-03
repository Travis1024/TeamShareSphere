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
 * @ClassName UserEnterprise
 * @Description 用户-企业-关联关系表
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Schema(description = "用户-企业-关联关系表")
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "team_user_enterprise")
public class UserEnterprise extends com.baomidou.mybatisplus.extension.activerecord.Model<UserEnterprise> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @Schema(description="用户ID")
    private Long userId;

    /**
     * 企业ID
     */
    @TableField(value = "enterprise_id")
    @Schema(description="企业ID")
    private Long enterpriseId;

    /**
     * 员工号
     */
    @TableField(value = "employee_number")
    @Schema(description="员工号")
    private String employeeNumber;

    /**
     * 角色（0-普通员工、1-企业管理员）
     */
    @TableField(value = "`role`")
    @Schema(description="角色（0-普通员工、1-企业管理员）")
    private Integer role;

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

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ENTERPRISE_ID = "enterprise_id";

    public static final String COL_EMPLOYEE_NUMBER = "employee_number";

    public static final String COL_ROLE = "role";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
