package org.travis.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<String> excludePath;
}
