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
 * @ClassName User
 * @Description 用户信息表
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
@Schema(description = "用户信息表")
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "team_user")
public class User extends com.baomidou.mybatisplus.extension.activerecord.Model<User> implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="ID")
    private Long id;

    /**
     * 登录用户名
     */
    @TableField(value = "username")
    @Schema(description="登录用户名")
    private String username;

    /**
     * 登录密码
     */
    @TableField(value = "`password`")
    @Schema(description="登录密码")
    private String password;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    @Schema(description="手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @Schema(description="邮箱")
    private String email;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    @Schema(description="出生日期")
    private Date birthday;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    @Schema(description="真实姓名")
    private String realName;

    /**
     * 头像URL
     */
    @TableField(value = "icon")
    @Schema(description="头像URL")
    private String icon;

    /**
     * 性别：0-男性，1-女性
     */
    @TableField(value = "gender")
    @Schema(description="性别：0-男性，1-女性")
    private Integer gender;

    /**
     * 通讯地址：省份
     */
    @TableField(value = "province")
    @Schema(description="通讯地址：省份")
    private String province;

    /**
     * 通讯地址：城市
     */
    @TableField(value = "city")
    @Schema(description="通讯地址：城市")
    private String city;

    /**
     * 通讯地址：区/县
     */
    @TableField(value = "district")
    @Schema(description="通讯地址：区/县")
    private String district;

    /**
     * 紧急联系人名字
     */
    @TableField(value = "emergency_contact_name")
    @Schema(description="紧急联系人名字")
    private String emergencyContactName;

    /**
     * 紧急联系人手机号码
     */
    @TableField(value = "emergency_contact_phone")
    @Schema(description="紧急联系人手机号码")
    private String emergencyContactPhone;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    @Schema(description="逻辑删除")
    @TableLogic
    private Long isDeleted;

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

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_PHONE = "phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_REAL_NAME = "real_name";

    public static final String COL_ICON = "icon";

    public static final String COL_GENDER = "gender";

    public static final String COL_PROVINCE = "province";

    public static final String COL_CITY = "city";

    public static final String COL_DISTRICT = "district";

    public static final String COL_EMERGENCY_CONTACT_NAME = "emergency_contact_name";

    public static final String COL_EMERGENCY_CONTACT_PHONE = "emergency_contact_phone";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_UPDATER = "updater";

    public static final String COL_CREATOR = "creator";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_TIME = "create_time";
}
