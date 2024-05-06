package org.travis.file.controller;
import cn.hutool.core.io.IoUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.travis.common.enums.BizCodeEnum;
import org.travis.common.exceptions.BadRequestException;
import org.travis.common.exceptions.CommonException;
import org.travis.file.entity.FileMediaInfo;
import org.travis.file.service.FileMediaInfoService;
import org.travis.file.service.impl.FileMediaInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
* (file_file_media_info)表控制层
*
* @author travis-wei
*/
@Slf4j
@RestController
@RequestMapping("/mediaInfo")
public class FileMediaInfoController {

    @Resource
    private FileMediaInfoService fileMediaInfoService;

    @Operation(summary = "获取视频-m3u8播放文件")
    @GetMapping("/m3u8")
    private void getM3u8File(@RequestParam("fileId") Long fileId, HttpServletResponse response) {
        try {
            ByteArrayInputStream byteArrayInputStream = fileMediaInfoService.getM3u8File(fileId);
            response.setHeader("Content-Type","application/octet-stream");
            IoUtil.copy(byteArrayInputStream, response.getOutputStream());
        } catch (IOException ioException) {
            log.error("[{}] 获取视频-m3u8播放文件异常! -> IO-流处理异常", fileId);
            throw new CommonException(BizCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ioException.getMessage());
        }
    }


    @Operation(summary = "获取视频密钥文件")
    @GetMapping("/getKeyFile")
    private void getKeyFile(@RequestParam("fileId") Long fileId, @RequestParam("token") String token, HttpServletResponse response) {
        // 请求示例：http://#{gatewayAddr}/file/mediaInfo/getKeyFile?fileId=#{fileId}&token=#{token}
        try {
            InputStream inputStream = fileMediaInfoService.getKeyFile(fileId, token);
            response.setHeader("Content-Type","application/octet-stream");
            IoUtil.copy(inputStream, response.getOutputStream());
        } catch (IOException ioException) {
            log.error("[{}] 获取视频密钥文件异常! -> IO-流处理异常", fileId);
            throw new CommonException(BizCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ioException.getMessage());
        }
    }
}
