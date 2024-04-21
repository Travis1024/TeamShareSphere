package org.travis.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PathFilterProperties
 * @Description 路径过滤配置信息
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "tss.path-filter")
public class PathFilterProperties {

    private volatile Set<String> excludePath;

    @PostConstruct
    public void setExcludePath() {
        if (excludePath == null) {
            synchronized (PathFilterProperties.class) {
                if (excludePath == null) {
                    excludePath = new HashSet<>();
                }
            }
        }

        // swagger 路径排除
        excludePath.add("/favicon.ico");
        excludePath.add("/v2/**");
        excludePath.add("/v3/**");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/webjars/**");
        excludePath.add("/doc.html");
    }
}
