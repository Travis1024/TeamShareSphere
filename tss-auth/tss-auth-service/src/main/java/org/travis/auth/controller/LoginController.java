package org.travis.auth.controller;

import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.travis.api.client.team.TeamClient;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.common.domain.R;

import javax.annotation.Resource;

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
    @Resource
    private TeamClient teamClient;

    @GetMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("登录中：{}", DateUtil.date());
        UserCheckInfoDTO userCheckInfoDTO = new UserCheckInfoDTO();
        userCheckInfoDTO.setUsername(username);
        userCheckInfoDTO.setPassword(password);
        String checked = teamClient.checkUserInfoAndPassword(userCheckInfoDTO);
        // 如果查询成功，则进行登录
        if (StrUtil.isNotEmpty(checked) && !"-1".equals(checked)) {
            StpUtil.login(checked);
        }
    }

    @GetMapping("/logout")
    public void logout() {
        StpUtil.logout();
    }


    @GetMapping("/test1")
    public String test1() {
        String dateStr = DateUtil.date().toDateStr();
        log.info("test1");
        log.info("当前时间:{}", dateStr);
        return dateStr;
    }

    @GetMapping("/test2")
    public String test2() {
        String dateStr = DateUtil.date().toDateStr();
        log.info("test2");
        log.info("当前时间:{}", dateStr);
        return dateStr;
    }

    @GetMapping("/test3")
    public String test3() {
        String dateStr = DateUtil.date().toDateStr();
        log.info("test3");
        log.info("当前时间:{}", dateStr);
        return dateStr;
    }
}
