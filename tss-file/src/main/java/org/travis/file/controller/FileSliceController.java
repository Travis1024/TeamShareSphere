package org.travis.file.controller;
import org.travis.file.service.FileSliceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (file_file_slice)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/slice")
public class FileSliceController {

    @Resource
    private FileSliceService fileSliceService;

}
