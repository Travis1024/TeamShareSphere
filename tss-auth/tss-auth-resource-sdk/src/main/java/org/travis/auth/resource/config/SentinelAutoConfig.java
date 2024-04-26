package org.travis.auth.resource.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SentinelAutoConfig
 * @Description SentinelAutoConfig
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/26
 */
@Configuration
public class SentinelAutoConfig {
    @Bean
    public SentinelResourceAspect getSentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
