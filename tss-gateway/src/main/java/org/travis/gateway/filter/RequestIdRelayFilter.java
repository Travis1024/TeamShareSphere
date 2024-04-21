package org.travis.gateway.filter;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.travis.common.constants.SystemConstant;
import reactor.core.publisher.Mono;

/**
 * @ClassName RequestIdRelayFilter
 * @Description 请求ID添加过滤器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class RequestIdRelayFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 生成 requestId
        String requestId = IdUtil.simpleUUID();
        // 将 requestId 保存到日志变量池
        MDC.put(SystemConstant.REQUEST_ID_HEADER, requestId);

        // 更新请求头
        ServerHttpRequest newRequest = exchange
                .getRequest()
                .mutate()
                // 添加 requestId 标识
                .header(SystemConstant.REQUEST_ID_HEADER, requestId)
                // 添加 请求来源：网关 标识
                .header(SystemConstant.REQUEST_FROM_HEADER, SystemConstant.GATEWAY_ORIGIN_NAME)
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }
}
