package org.travis.file.controller;
import org.travis.file.entity.FileMediaInfo;
import org.travis.file.service.impl.FileMediaInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (file_file_media_info)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/file_file_media_info")
public class FileMediaInfoController {

    @Resource
    private FileMediaInfoService fileMediaInfoService;

}
