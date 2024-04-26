package org.travis.auth.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.travis.common.exceptions.ServiceDegradedException;

/**
 * @ClassName ServiceDegradedHandler
 * @Description 服务降级处理函数
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/26
 */
@Slf4j
public class ServiceDegradedHandler {
    /**
     * 非同类需要配置为静态方法，否则找不到对应方法；
     */
    public static String commonBlockHandler(BlockException blockException) {
        log.warn("[服务降级-commonBlockHandler] -> {}", blockException.toString());
        throw new ServiceDegradedException("「服务降级」请稍后重试");
    }
}
