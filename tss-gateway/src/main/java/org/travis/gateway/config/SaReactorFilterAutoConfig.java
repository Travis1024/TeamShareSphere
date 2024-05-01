package org.travis.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SaReactorFilterAutoConfig
 * @Description 全部放行 SaToken 过滤器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/1
 */
@Configuration
public class SaReactorFilterAutoConfig {

    @Bean(name = "directPassSaReactorFilter")
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter().addExclude("/**");
    }
}
