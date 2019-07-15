package com.yun.base.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SnowflakeIdWorker 自动配置类
 * @author: yun
 * @createdOn: 2018/7/25 14:50.
 */
@Configuration
@ConditionalOnClass(SnowflakeIdWorker.class)
@EnableConfigurationProperties(SnowflakeIdWorkerProperties.class)
public class SnowflakeIdWorkerAutoConfigure {
    @Autowired
    private SnowflakeIdWorkerProperties snowflakeIdWorkerProperties;

    @Bean
    @ConditionalOnMissingBean
    SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(snowflakeIdWorkerProperties.getWorkerid(),
                snowflakeIdWorkerProperties.getDatacenterid());
    }
}
