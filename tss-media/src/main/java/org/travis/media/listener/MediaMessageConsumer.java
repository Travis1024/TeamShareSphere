package org.travis.media.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.travis.api.dto.file.FileInfoSlimDTO;
import org.travis.common.constants.RocketMqConstant;
import org.travis.media.service.MediaService;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

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
public class MediaMessageConsumer implements RocketMQListener<FileInfoSlimDTO>, RocketMQPushConsumerLifecycleListener {
    @Resource
    private MediaService mediaService;
    @Resource
    private RedissonClient redissonClient;

    @Override
    public void onMessage(FileInfoSlimDTO fileInfoSlimDTO) {
        Long fileId = fileInfoSlimDTO.getId();
        log.info("视频文件ID：{}", fileId);

        // 业务处理之前 redisson 加锁，防止重复消费 - (60分钟)
        RBucket<Object> bucket = redissonClient.getBucket("media:" + fileId);
        if (bucket.setIfAbsent(true, Duration.of(60, ChronoUnit.MINUTES))) {
            log.info("[{}] 视频文件开始处理!", fileId);
            mediaService.handlerMedia(fileInfoSlimDTO);
        } else {
            log.warn("[{}] 60 分钟内出现重复消费!", fileId);
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        // 设置消息最小消费线程数量
        defaultMQPushConsumer.setConsumeThreadMin(2);
        // 设置消息最大消费线程数量
        defaultMQPushConsumer.setConsumeThreadMax(2);
        // 设置每次从队列中拉取的消息数
        defaultMQPushConsumer.setPullBatchSize(2);
    }
}
