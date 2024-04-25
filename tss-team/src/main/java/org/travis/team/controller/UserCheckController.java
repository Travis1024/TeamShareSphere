package org.travis.team.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.travis.api.dto.team.UserCheckInfoDTO;
import org.travis.common.domain.R;
import org.travis.common.enums.BizCodeEnum;

/**
 * @ClassName LoginController
 * @Description LoginController
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/22
 */
@Slf4j
@RestController
public class UserCheckController {

    @PostMapping(value = "/user/check")
    public String checkUserInfoAndPassword(@RequestBody UserCheckInfoDTO userCheckInfoDTO) throws InterruptedException {

        log.warn("sleep:{}", DateUtil.date());
        Thread.sleep(30000);
        log.warn("sleep:{}", DateUtil.date());

        if ("throw".equals(userCheckInfoDTO.getUsername())) {
            throw new RuntimeException("测试抛出异常");
        }

        if ("admin".equals(userCheckInfoDTO.getUsername()) && "123456".equals(userCheckInfoDTO.getPassword())) {
            return JSONUtil.toJsonStr(R.ok(12879709126912940L));
        } else {
            return JSONUtil.toJsonStr(R.error(BizCodeEnum.BAD_REQUEST.getCode(), "用户名或密码错误!"));
        }
    }
}
