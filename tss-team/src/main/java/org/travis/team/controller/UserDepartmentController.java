package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.UserDepartmentService;

import javax.annotation.Resource;

/**
* (team_user_department)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/userDepartment")
public class UserDepartmentController {

    @Resource
    private UserDepartmentService userDepartmentService;

}
