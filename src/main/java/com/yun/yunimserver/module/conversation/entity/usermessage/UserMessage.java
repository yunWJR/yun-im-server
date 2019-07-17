package com.yun.yunimserver.module.conversation.entity.usermessage;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.base.jpa.BaseEntity.BaseEntityWithAutoIdDate;
import com.yun.yunimserver.module.conversation.entity.ConversationMessage;
import com.yun.yunimserver.module.conversation.entity.ConversationMessageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * @author: yun
 * @createdOn: 2019-07-02 14:39.
 */

@Entity
@Data
@NoArgsConstructor
public class UserMessage extends BaseEntityWithAutoIdDate {
    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long sendUserId;

    @Column(nullable = false)
    private Integer platformType;

    @Column(nullable = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long messageId;

    @Column(nullable = false)
    private Long messageCreateTime;

    @Embedded
    @JsonUnwrapped
    private ConversationMessageInfo info;

    public UserMessage(Long userId, ConversationMessage msg) {
        this.userId = userId;

        if (msg != null) {
            this.messageId = msg.getId();
            this.messageCreateTime = msg.getCreateTime();
            this.platformType = msg.getPlatformType();
            this.sendUserId = msg.getSendUserId();
            this.info = msg.getInfo();
        }
    }
}