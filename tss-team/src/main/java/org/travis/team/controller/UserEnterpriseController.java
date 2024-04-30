package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.UserEnterpriseService;

import javax.annotation.Resource;

/**
* (tss_team.team_user_enterprise)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/user_enterprise")
public class UserEnterpriseController {

    @Resource
    private UserEnterpriseService userEnterpriseService;

}
