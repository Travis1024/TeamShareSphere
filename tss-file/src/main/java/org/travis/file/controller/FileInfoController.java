package org.travis.file.controller;

import org.travis.file.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (file_file_info)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/info")
public class FileInfoController {

    @Resource
    private FileInfoService fileInfoService;


}
