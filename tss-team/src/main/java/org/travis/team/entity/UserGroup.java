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
 * @ClassName UserGroup
 * @Description 用户-团队-关联关系表
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Schema(description = "用户-团队-关联关系表")
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tss_team.team_user_group")
public class UserGroup extends com.baomidou.mybatisplus.extension.activerecord.Model<UserGroup> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @Schema(description="用户ID")
    private Long userId;

    /**
     * 团队ID
     */
    @TableField(value = "group_id")
    @Schema(description="团队ID")
    private Long groupId;

    /**
     * 角色（0-普通员工、1-团队管理员）
     */
    @TableField(value = "`role`")
    @Schema(description="角色（0-普通员工、1-团队管理员）")
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

    public static final String COL_GROUP_ID = "group_id";

    public static final String COL_ROLE = "role";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
