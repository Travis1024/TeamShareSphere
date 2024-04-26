package org.travis.auth.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceDegradedHandler
 * @Description 服务降级处理函数
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/26
 */
@Slf4j
@Component
public class ServiceDegradedHandler {

    public String commonBlockHandler(BlockException blockException) {
        log.warn("[服务降级-commonBlockHandler] -> {}", blockException.toString());
        return "「服务降级」请稍后重试";
    }
}
