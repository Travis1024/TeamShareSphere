package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.entity.Enterprise;
import org.travis.team.service.EnterpriseService;

import javax.annotation.Resource;
import java.util.List;

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


    @GetMapping("/queryAll")
    private List<Enterprise> queryAll() {
        return enterpriseService.queryAll();
    }
}
