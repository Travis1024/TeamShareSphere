package org.travis.auth.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.travis.auth.resource.interceptor.UserInfoInterceptor;

/**
 * @ClassName InterceptorConfig
 * @Description 拦截器配置类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加用户信息拦截器
        registry.addInterceptor(new UserInfoInterceptor()).order(0);
    }
}
