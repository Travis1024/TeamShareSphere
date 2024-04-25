package org.travis.common.config.redisson;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RedissonAutoConfig
 * @Description Redisson 配置类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
@ConditionalOnClass({RedissonClient.class, Redisson.class})
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedissonAutoConfig {

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";
    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        /*
          读取 redis 配置信息
         */
        // 读取集群信息
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        // 读取哨兵信息
        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        // 读取密码信息
        String password = redisProperties.getPassword();
        // 读取数据库序号信息
        int database = redisProperties.getDatabase();
        // 读取初始化连接超时时间
        Duration redisPropertiesTimeout = redisProperties.getTimeout();
        // 设置用户自定义超时时间
        int timeout = redisPropertiesTimeout != null ? Long.valueOf(redisPropertiesTimeout.toMillis()).intValue() : 3000;

        /*
          设置 redisson 配置
         */
        // 初始化配置类
        Config config = new Config();

        // 配置 redis 模式
        if (cluster != null && CollUtil.isNotEmpty(cluster.getNodes())) {
            // (1) 集群模式
            config.useClusterServers()
                    .addNodeAddress(convert(cluster.getNodes()))
                    .setConnectTimeout(timeout)
                    .setPassword(password);
        } else if (sentinel != null && StrUtil.isNotEmpty(sentinel.getMaster())) {
            // (2) 哨兵模式
            config.useSentinelServers()
                    .setMasterName(sentinel.getMaster())
                    .addSentinelAddress(convert(sentinel.getNodes()))
                    .setConnectTimeout(timeout)
                    .setDatabase(database)
                    .setPassword(password);
        } else {
            // (3) 单机模式
            config.useSingleServer()
                    .setAddress(StrUtil.format("redis://{}:{}", redisProperties.getHost(), redisProperties.getPort()))
                    .setConnectTimeout(timeout)
                    .setDatabase(database)
                    .setPassword(password);
        }
        // 配置 redisson 序列化
        Codec codec = new JsonJacksonCodec();
        config.setCodec(codec);
        return Redisson.create(config);
    }


    /**
     * @MethodName convert
     * @Description Redis 集群地址转换
     * @Author travis-wei
     * @Data 2024/4/21
     * @param nodeList	redis 集群地址
     * @Return java.lang.String[]
     **/
    private String[] convert(List<String> nodeList) {
        List<String> nodes = new ArrayList<>(nodeList.size());
        for (String node : nodeList) {
            if (!node.startsWith(REDIS_PROTOCOL_PREFIX) && !node.startsWith(REDISS_PROTOCOL_PREFIX)) {
                nodes.add(REDIS_PROTOCOL_PREFIX + node);
            } else {
                nodes.add(node);
            }
        }
        return nodes.toArray(new String[0]);
    }
}
