package org.travis.team.controller;
import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.travis.team.entity.Enterprise;
import org.travis.team.pojo.dto.EnterpriseInsertDTO;
import org.travis.team.service.EnterpriseService;

import javax.annotation.Resource;
import java.util.List;

/**
* (tss_team.tss_team.team_enterprise)表控制层
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


    @PostMapping("/insert")
    @Operation(summary = "新增企业信息")
    private void insertOne(@Validated @RequestBody EnterpriseInsertDTO enterpriseInsertDTO) {
        enterpriseService.insertOne(enterpriseInsertDTO);
    }
}
