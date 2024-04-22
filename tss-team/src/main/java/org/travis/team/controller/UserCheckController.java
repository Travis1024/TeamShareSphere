package org.travis.team.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.travis.api.dto.team.UserCheckInfoDTO;

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

    @PostMapping("/user/check")
    public long checkUserInfoAndPassword(@RequestBody UserCheckInfoDTO userCheckInfoDTO) {
        return "admin".equals(userCheckInfoDTO.getUsername()) && "123456".equals(userCheckInfoDTO.getPassword()) ? 12879709126912940L : -1L;
    }
}
