package org.travis.auth.resource.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.same.SaSameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName SaTokenConfig
 * @Description Sa-Token 权限认证 配置类（服务间内部调用鉴权）
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证，服务间内部调用鉴权
                    String token = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);
                    SaSameUtil.checkToken(token);
                })
                // 异常处理方法
                .setError(e -> R.error(BizCodeEnum.TOKEN_CHECK_FAILED.getCode(), "Same-Token 服务内部调用鉴权失败:" + e.getMessage()));
    }
}
