package org.travis.gateway.handler;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.travis.gateway.config.PathFilterProperties;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName PathFilterTriggerHandler
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Slf4j
@Component
public class PathFilterTriggerHandler {
    @Resource
    public SaReactorFilter saReactorFilter;
    @Resource
    public PathFilterProperties pathFilterProperties;
    @Resource(name = "singleThreadPool")
    private ExecutorService executorService;

    public void refreshPathFilter() {
        executorService.submit(() -> {
            try {
                Thread.sleep(3000);
                saReactorFilter.setExcludeList(pathFilterProperties.getExcludePath());
                log.info("[PathFilterProperties Update]");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
