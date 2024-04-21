package org.travis.auth.controller;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
