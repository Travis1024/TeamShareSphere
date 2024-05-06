package org.travis.file.controller;

import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.travis.common.constants.RocketMqConstant;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;
import org.travis.file.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    @Resource
    private RocketMQTemplate rocketMqTemplate;

    @Operation(summary = "RocketMQ-消息生产者")
    @GetMapping("/mq")
    private void sendMqMessage(@RequestParam("fileId") Long fileId) {
        rocketMqTemplate.asyncSend(RocketMqConstant.TOPIC_MEDIA_NAME, MessageBuilder.withPayload(fileId).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("MQ 消息发送成功：{}", fileId);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("MQ 消息发送失败：{}, 原因：{}", fileId, throwable.getMessage());
            }
        });
    }

}
