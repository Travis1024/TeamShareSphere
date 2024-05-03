package org.travis.auth.main.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.travis.api.client.team.TeamClient;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.auth.main.handler.ServiceDegradedHandler;
import org.travis.common.domain.R;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @ClassName LoginController
 * @Description LoginController
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
@RestController
public class LoginController {
    @DubboReference(mock = "org.travis.auth.main.mock.TeamClientMock")
    private TeamClient teamClient;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/login")
    @Operation(summary = "用户登录")
    public R<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StpUtil.isLogin()) {
            return R.ok("用户已登录!");
        }

        log.info("登录中：{}", DateUtil.date());
        UserCheckInfoDTO userCheckInfoDTO = new UserCheckInfoDTO();
        userCheckInfoDTO.setUsername(username);
        userCheckInfoDTO.setPassword(password);

        R<Long> result = teamClient.checkUserInfoAndPassword(userCheckInfoDTO);
        log.warn("login-check: {}", result);

        // 用户登录成功
        if (result.checkSuccess()) {
            StpUtil.login(result.getData());
            return R.ok();
        }
        // 用户登录失败
        return result.autoSetRequestId();
    }

    @GetMapping("/logout")
    @Operation(summary = "用户登录状态注销")
    public void logout() {
        StpUtil.logout();
    }


    @SentinelResource(value = "func-test1", blockHandlerClass = ServiceDegradedHandler.class, blockHandler = "commonBlockHandler")
    @GetMapping("/test")
    @Operation(summary = "sentinel-测试接口")
    public String test() {
        log.info("test -> 当前时间: {}", DateUtil.date());
        return "success";
    }
}
