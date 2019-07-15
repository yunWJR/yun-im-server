package com.yun.yunimserver.module.imservice.dtovo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.imservice.entity.ConversationMessageInfo;
import com.yun.yunimserver.module.imservice.entity.usermessage.UserMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-08 10:17.
 */

@Data
@NoArgsConstructor
public class MessageVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    private Long createTime;

    private Long updateTime;

    private Long sendDate;  // todo

    @JsonUnwrapped
    private ConversationMessageInfo info;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long sendUserId;

    private Integer platformType;

    public MessageVo(UserMessage userMsg) {
        if (userMsg != null) {
            this.id = userMsg.getMessageId();
            this.createTime = userMsg.getMessageCreateTime();
            this.updateTime = userMsg.getUpdateTime();
            this.createTime = userMsg.getMessageCreateTime();
            this.info = userMsg.getInfo();
            this.sendUserId = userMsg.getSendUserId();
            this.platformType = userMsg.getPlatformType();
        }
    }

    @JsonIgnore
    public Long getConversationId() {
        return getInfo().getConversationId();
    }
}
