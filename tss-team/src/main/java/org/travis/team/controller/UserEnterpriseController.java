package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.UserEnterpriseService;

import javax.annotation.Resource;

/**
* (team_user_enterprise)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/userEnterprise")
public class UserEnterpriseController {

    @Resource
    private UserEnterpriseService userEnterpriseService;

}
