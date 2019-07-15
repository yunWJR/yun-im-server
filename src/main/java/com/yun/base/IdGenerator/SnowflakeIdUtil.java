package com.yun.base.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * IdentifierGenerator不能注入 bean，使用该类的静态对象来接收注入的 bean
 * @author: yun
 * @createdOn: 2018/7/25 15:15.
 */

@Component
public class SnowflakeIdUtil {
    public static SnowflakeIdUtil snowflakeIdUtil;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    public SnowflakeIdUtil() {
    }

    @PostConstruct
    public void init() {
        snowflakeIdUtil = this;
        snowflakeIdUtil.snowflakeIdWorker = this.snowflakeIdWorker;
    }

    public SnowflakeIdWorker getSnowflakeIdWorker() {
        return snowflakeIdWorker;
    }

    public void setSnowflakeIdWorker(SnowflakeIdWorker snowflakeIdWorker) {
        this.snowflakeIdWorker = snowflakeIdWorker;
    }
}
