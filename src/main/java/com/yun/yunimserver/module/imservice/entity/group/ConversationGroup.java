package com.yun.yunimserver.module.imservice.entity.group;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.base.jpa.BaseEntity.BaseEntityWithGlIdDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * 群组聊天
 * @author: yun
 * @createdOn: 2018/7/26 13:46.
 */

@Entity
@Data
@ApiModel("群聊")
public class ConversationGroup extends BaseEntityWithGlIdDate {

    @Embedded
    @JsonUnwrapped
    private ConversationGroupInfo info;

    @Column
    @ApiModelProperty("最多成员数")
    private Integer maxMeb = 100;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long adminUserId;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUserId;

    public ConversationGroup() {
    }

    public ConversationGroup(Long createUserId) {
        this.adminUserId = createUserId;
        this.createUserId = createUserId;
    }
}
