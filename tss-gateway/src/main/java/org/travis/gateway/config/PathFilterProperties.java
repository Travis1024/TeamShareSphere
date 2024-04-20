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

    private Set<String> excludePath;

    @PostConstruct
    public void setExcludePath() {
        if (excludePath == null) {
            excludePath = new HashSet<>();
        }

        // 添加默认不拦截的路径（此处路径已经将服务前缀过滤掉）
        excludePath.add("/auth/login");
        excludePath.add("/auth/admin/login");
        // swagger 路径排除
        excludePath.add("/v2/**");
        excludePath.add("/v3/**");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/webjars/**");
        excludePath.add("/doc.html");
    }
}
