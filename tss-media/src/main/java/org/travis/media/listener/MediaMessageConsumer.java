package org.travis.media.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.travis.common.constants.RocketMqConstant;

/**
 * @ClassName MediaMessageConsumer
 * @Description MediaMessageConsumer
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = RocketMqConstant.TOPIC_MEDIA_NAME, consumerGroup = RocketMqConstant.CONSUMER_GROUP_MEDIA_NAME)
public class MediaMessageConsumer implements RocketMQListener<Long> {
    @Override
    public void onMessage(Long fileId) {
        // TODO 处理视频切片消息逻辑
        log.info("视频文件ID：{}", fileId);
    }
}
