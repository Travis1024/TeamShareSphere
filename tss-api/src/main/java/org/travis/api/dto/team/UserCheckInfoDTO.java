package org.travis.api.dto.team;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName UserCheckInfoDTO
 * @Description UserCheckInfoDTO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Data
public class UserCheckInfoDTO implements Serializable {
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "登录用户名不能为空!")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "登录密码不能为空!")
    private String password;
}
