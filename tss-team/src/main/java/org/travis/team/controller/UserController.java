package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.UserService;

import javax.annotation.Resource;

/**
* (tss_team.team_user)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

}
