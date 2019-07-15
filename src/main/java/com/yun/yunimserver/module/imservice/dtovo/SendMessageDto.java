package com.yun.yunimserver.module.imservice.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.yun.yunimserver.module.imservice.entity.ConversationMessageInfo;
import lombok.Data;

/**
 * @author: yun
 * @createdOn: 2018/7/26 10:33.
 */

@Data
public class SendMessageDto {
    @JsonUnwrapped
    private ConversationMessageInfo info;
}