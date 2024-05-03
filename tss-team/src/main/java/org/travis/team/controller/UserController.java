package org.travis.team.controller;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.entity.User;
import org.travis.team.service.UserService;
import org.travis.team.pojo.vo.UserSlimVO;

import javax.annotation.Resource;

/**
* (team_user)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/query")
    @Operation(summary = "查询当前登录用户详细信息")
    private User queryCurrentUser() {
        long userId = StpUtil.getLoginIdAsLong();
        return userService.queryUserById(userId);
    }

    @GetMapping("/querySlim")
    private UserSlimVO queryCurrentSlimUser() {
        long userId = StpUtil.getLoginIdAsLong();
        return userService.querySlimUserById(userId);
    }
}
