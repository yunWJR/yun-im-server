package com.yun.yunimserver.module.imservice.entity.couple;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author: yun
 * @createdOn: 2019-07-02 13:59.
 */

@Embeddable
@ApiModel("单聊内容")
@Data
public class ConversationCoupleInfo {

    @Column(nullable = false)
    @NotNull
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty("创建人ID")
    private Long createUserId;

    @Column(nullable = false)
    @NotNull
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long anotherUserId;

    public ConversationCoupleInfo(@NotNull Long createUserId, @NotNull Long anotherUserId) {
        this.createUserId = createUserId;
        this.anotherUserId = anotherUserId;
    }

    public ConversationCoupleInfo() {
    }
}
