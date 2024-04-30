package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.DepartmentService;

import javax.annotation.Resource;

/**
* (tss_team.team_department)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

}
