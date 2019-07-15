package com.yun.base.IdGenerator;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * snowflakeid 配置信息
 * @author: yun
 * @createdOn: 2018/7/25 14:50.
 */
@ConfigurationProperties("snowflakeidworker")
public class SnowflakeIdWorkerProperties {
    /**
     * 工作机器ID(0~31)
     */
    private long workerid;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterid;

    public long getWorkerid() {
        return workerid;
    }

    public void setWorkerid(long workerid) {
        this.workerid = workerid;
    }

    public long getDatacenterid() {
        return datacenterid;
    }

    public void setDatacenterid(long datacenterid) {
        this.datacenterid = datacenterid;
    }

}
