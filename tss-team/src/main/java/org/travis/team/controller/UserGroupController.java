package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.UserGroupService;

import javax.annotation.Resource;

/**
* (team_user_group)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Resource
    private UserGroupService userGroupService;

}
