package org.travis.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @ClassName SaTokenConfig
 * @Description SaToken配置类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Configuration
public class SaTokenConfig {
    @Resource
    private PathFilterProperties pathFilterProperties;

    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/**")
                .setExcludeList(new ArrayList<>(pathFilterProperties.getExcludePath()))
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除配置文件中的开放路径
                    SaRouter.match("/**", r -> StpUtil.checkLogin());

                    // 权限认证 -- 不同模块, 校验不同权限
                    SaRouter.match("/system/**", r -> StpUtil.checkPermission("system"));
                })
                // 异常处理方法
                .setError(e -> SaResult.error(e.getMessage()));
    }
}
