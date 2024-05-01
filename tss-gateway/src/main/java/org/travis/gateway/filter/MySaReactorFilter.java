package org.travis.gateway.filter;

import cn.dev33.satoken.reactor.context.SaReactorHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import org.travis.common.constants.SystemConstant;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;
import org.travis.gateway.config.PathFilterProperties;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @ClassName MySaReactorFilter
 * @Description SaReactorFilter 增强过滤器
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Slf4j
@Order(-100)
@Component
public class MySaReactorFilter extends SaReactorFilter {
    @Resource
    private PathFilterProperties pathFilterProperties;
    @Resource
    private SaReactorFilter directPassSaReactorFilter;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public MySaReactorFilter() {
        super.addInclude("/**")
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除配置文件中的开放路径
                    SaRouter.match("/**", r -> StpUtil.checkLogin());
                    // 权限认证 -- 不同模块, 校验不同权限
                    SaRouter.match("/system/**", r -> StpUtil.checkPermission("system"));
                })
                // 异常处理方法
                .setError(e -> {
                    log.warn("用户登录验证失败!");
                    return R.error(BizCodeEnum.TOKEN_CHECK_FAILED.getCode(), "用户登录验证失败: " + e.getMessage());
                });
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 获取请求 Request 信息
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String antPath = serverHttpRequest.getPath().toString();

        // ServerHttpRequest newRequest;
        // ServerWebExchange newExchange;

        if (isExcludePath(antPath)) {
            // 无需鉴权
            log.info("[Direct Pass] -> URL: {}", antPath);
            // 如果路径无需鉴权，直接放行，添加请求头
            // newRequest = exchange.getRequest().mutate().header(SystemConstant.IS_NEED_AUTH, "NO").build();
            // newExchange = exchange.mutate().request(newRequest).build();
            return directPassSaReactorFilter.filter(exchange, chain);
        } else {
            // 需要鉴权（添加请求头）
            log.info("[Wait for Authentication] -> URL: {}", antPath);
            // newRequest = exchange.getRequest().mutate().header(SystemConstant.IS_NEED_AUTH, "YES").build();
            // newExchange = exchange.mutate().request(newRequest).build();
            return super.filter(exchange, chain);
        }
    }


    /**
     * @MethodName isExcludePath
     * @Description 判断当前请求路径是否是无需鉴权的路径
     * @Author travis-wei
     * @Data 2024/4/22
     * @param antPath	当前请求路径
     * @Return boolean
     **/
    private boolean isExcludePath(String antPath) {
        for (String pathPattern : pathFilterProperties.getExcludePath()) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }
}
