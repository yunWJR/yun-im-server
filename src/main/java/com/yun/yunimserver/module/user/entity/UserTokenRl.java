package com.yun.yunimserver.module.user.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.base.jpa.BaseEntity.BaseEntityWithAutoIdDate;
import com.yun.yunimserver.module.ClientPlatformType;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author: yun
 * @createdOn: 2019-07-02 15:06.
 */

@Entity
public class UserTokenRl extends BaseEntityWithAutoIdDate {
    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @Column
    private String token;

    @Column(nullable = false)
    @ApiModelProperty(ClientPlatformType.des)
    private Integer platform;
}
