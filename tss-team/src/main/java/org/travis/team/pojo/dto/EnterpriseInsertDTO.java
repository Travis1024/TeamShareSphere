package org.travis.team.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName EnterpriseInsertDTO
 * @Description EnterpriseInsertDTO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/2
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnterpriseInsertDTO implements Serializable {
    /**
     * 企业名称
     */
    @Schema(description="企业名称")
    @NotBlank(message = "企业名称不能为空!")
    private String name;

    /**
     * 企业简述
     */
    @Schema(description="企业简述")
    @NotBlank(message = "企业简述不能为空!")
    private String description;

    /**
     * 企业管理者联系电话
     */
    @Schema(description="企业管理者联系电话")
    private String managerPhone;

    /**
     * 企业管理者邮箱地址
     */
    @Schema(description="企业管理者邮箱地址")
    private String managerEmail;
}
