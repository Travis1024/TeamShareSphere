package org.travis.team.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName UserSlimVO
 * @Description TODO
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
public class UserSlimVO implements Serializable {
    /**
     * ID
     */
    @Schema(description="ID")
    private Long id;

    /**
     * 登录用户名
     */
    @Schema(description="登录用户名")
    private String username;

    /**
     * 手机号码
     */
    @Schema(description="手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description="邮箱")
    private String email;

    /**
     * 真实姓名
     */
    @Schema(description="真实姓名")
    private String realName;

    /**
     * 头像URL
     */
    @Schema(description="头像URL")
    private String icon;
}
