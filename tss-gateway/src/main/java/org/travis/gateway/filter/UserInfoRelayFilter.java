package org.travis.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.travis.common.constants.SystemConstant;
import reactor.core.publisher.Mono;

/**
 * @ClassName UserInfoRelayFilter
 * @Description 将 userId 添加到请求头中
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Order(0)
public class UserInfoRelayFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        /**
         * 处于未登录状态
         */
        if (!StpUtil.isLogin()) {
            return chain.filter(exchange);
        }

        /**
         * 处于登录状态
         */
        // 获取 userId
        long userId = StpUtil.getLoginIdAsLong();

        // 更新请求头
        ServerHttpRequest newRequest = exchange
                .getRequest()
                .mutate()
                // 添加 requestId 标识
                .header(SystemConstant.USER_ID_HEADER, String.valueOf(userId))
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }
}
