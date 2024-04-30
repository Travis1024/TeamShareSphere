package org.travis.team.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.travis.team.service.GroupService;

import javax.annotation.Resource;

/**
* (tss_team.team_group)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    @Resource
    private GroupService groupService;

}
