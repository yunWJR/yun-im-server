package com.yun.yunimserver.module.imservice.entity.couple;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.yun.base.jpa.BaseEntity.BaseEntityWithGlIdDate;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * 两人对话
 * @author: yun
 * @createdOn: 2018/8/3 14:37.
 */
@Entity
@Data
@ApiModel("单聊")
public class ConversationCouple extends BaseEntityWithGlIdDate {

    @Embedded
    @JsonUnwrapped
    private ConversationCoupleInfo info;

    public ConversationCouple() {
    }

    public ConversationCouple(Long createUserId, Long anotherUserId) {
        this.info = new ConversationCoupleInfo(createUserId, anotherUserId);
    }
}
