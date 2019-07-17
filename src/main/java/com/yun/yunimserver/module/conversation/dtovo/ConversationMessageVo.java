package com.yun.yunimserver.module.conversation.dtovo;

import lombok.Data;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-08 10:31.
 */

@Data
public class ConversationMessageVo {
    private List<ConversationVo> conversationList;

    private Long lastTime;
}
