package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.EnterpriseService;

import javax.annotation.Resource;

/**
* (tss_team.team_enterprise)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Resource
    private EnterpriseService enterpriseService;

}
