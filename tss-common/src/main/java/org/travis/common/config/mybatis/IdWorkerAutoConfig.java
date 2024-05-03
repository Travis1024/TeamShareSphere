package org.travis.common.config.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.ImadcnIdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName IdWorkerAutoConfig
 * @Description IdWorkerAutoConfig
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/2
 */
@Configuration
public class IdWorkerAutoConfig {
    @Value("${mybatis-plus.zookeeper.serverAddr}")
    private String zookeeperAddr;

    @Bean
    public IdentifierGenerator getIdGenerator() {
        // ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration();
        // zookeeperConfiguration.setServerLists(zookeeperAddr);
        // ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        // applicationConfiguration.setSerialize(SerializeStrategy.SERIALIZE_JSON_JACKSON);
        // return new ImadcnIdentifierGenerator(zookeeperConfiguration, applicationConfiguration);
        return new ImadcnIdentifierGenerator(zookeeperAddr);
    }
}
