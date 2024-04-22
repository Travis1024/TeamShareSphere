package org.travis.gateway.handler;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PathFilterBeanListenerHandler
 * @Description PathFilterBeanListenerHandler
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Slf4j
@Component
public class PathFilterBeanListenerHandler {
    @Resource
    private PathFilterTriggerHandler pathFilterTriggerHandler;

    private final NacosConfigManager nacosConfigManager;

    public PathFilterBeanListenerHandler(NacosConfigManager nacosConfigManager) {
        this.nacosConfigManager = nacosConfigManager;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void refreshPathFilter() throws NacosException {
        AbstractConfigChangeListener listener = new AbstractConfigChangeListener() {
            @Override
            public void receiveConfigChange(ConfigChangeEvent configChangeEvent) {
                List<ConfigChangeItem> collect = new ArrayList<>(configChangeEvent.getChangeItems());
                for (ConfigChangeItem configChangeItem : collect) {
                    if (StrUtil.isNotEmpty(configChangeItem.getKey()) && configChangeItem.getKey().startsWith("tss.path-filter.exclude-path")) {
                        pathFilterTriggerHandler.refreshPathFilter();
                        break;
                    }
                }
            }
        };
        this.nacosConfigManager.getConfigService().addListener("shared-path-filter.yaml", "DEFAULT_GROUP", listener);
    }
}
