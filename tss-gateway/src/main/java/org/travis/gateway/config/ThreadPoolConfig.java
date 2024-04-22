package org.travis.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolConfig
 * @Description 线程池配置类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "singleThreadPool")
    public ExecutorService singleThreadPool() {
        return new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }
}
