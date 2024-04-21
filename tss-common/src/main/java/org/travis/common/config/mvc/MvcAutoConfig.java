package org.travis.common.config.mvc;

import org.travis.common.config.mvc.advice.CommonExceptionAdvice;
import org.travis.common.config.mvc.advice.WrapperResponseBodyAdvice;
import org.travis.common.filter.RequestIdFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @ClassName MvcAutoConfig
 * @Description MVC 相关类注入
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Configuration
@ConditionalOnClass({CommonExceptionAdvice.class, Filter.class})
public class MvcAutoConfig implements WebMvcConfigurer {
    /**
     * 将「统一异常处理类」注入 Bean 容器
     */
    @Bean
    public CommonExceptionAdvice commonExceptionAdvice() {
        return new CommonExceptionAdvice();
    }

    /**
     * 将「请求 ID 过滤器」注入 Bean 容器
     */
    @Bean
    public RequestIdFilter requestIdFilter() {
        return new RequestIdFilter();
    }

    /**
     * 将「响应信息包装类」注入 Bean 容器
     */
    @Bean
    public WrapperResponseBodyAdvice wrapperResponseBodyAdvice() {
        return new WrapperResponseBodyAdvice();
    }
}
