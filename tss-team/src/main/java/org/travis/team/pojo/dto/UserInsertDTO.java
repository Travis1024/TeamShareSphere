package org.travis.team.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.travis.team.entity.User;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @ClassName UserInsertDTO
 * @Description UserInsertDTO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/3
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInsertDTO {
    /**
     * 登录用户名
     */
    @NotBlank(message = "登录用户名不能为空!")
    @Schema(description="登录用户名")
    private String username;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空!")
    @Schema(description="登录密码")
    private String password;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空!")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请检查手机号码格式!")
    @Schema(description="手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空!")
    @Email(message = "请检查邮箱格式!")
    @Schema(description="邮箱")
    private String email;

    /**
     * 出生日期
     */
    @Schema(description="出生日期")
    private Date birthday;

    /**
     * 真实姓名
     */
    @Schema(description="真实姓名")
    @NotBlank(message = "真实姓名不能为空!")
    private String realName;

    /**
     * 头像URL
     */
    @Schema(description="头像URL")
    private String icon;

    /**
     * 性别：0-男性，1-女性
     */
    @Schema(description="性别：0-男性，1-女性")
    @Min(0)
    @Max(1)
    private Integer gender;

    /**
     * 通讯地址：省份
     */
    @Schema(description="通讯地址：省份")
    private String province;

    /**
     * 通讯地址：城市
     */
    @Schema(description="通讯地址：城市")
    private String city;

    /**
     * 通讯地址：区/县
     */
    @Schema(description="通讯地址：区/县")
    private String district;

    /**
     * 紧急联系人名字
     */
    @Schema(description="紧急联系人名字")
    @NotBlank(message = "紧急联系人名字不能为空!")
    private String emergencyContactName;

    /**
     * 紧急联系人手机号码
     */
    @Schema(description="紧急联系人手机号码")
    @NotBlank(message = "紧急联系人手机号码不能为空!")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请检查紧急联系人手机号码格式!")
    private String emergencyContactPhone;
}
