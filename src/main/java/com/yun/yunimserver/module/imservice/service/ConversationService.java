package com.yun.yunimserver.module.imservice.service;

import com.yun.base.module.Controller.RqtStrIdsBean;
import com.yun.yunimserver.module.imservice.dtovo.ConversationMessageVo;
import com.yun.yunimserver.module.imservice.dtovo.ConversationVo;
import com.yun.yunimserver.module.imservice.dtovo.MessageVo;
import com.yun.yunimserver.module.imservice.dtovo.SendMessageDto;
import com.yun.yunimserver.module.imservice.entity.ConversationMessage;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018/8/3 15:42.
 */

public interface ConversationService {
    List<Long> getUserIdsImMessage(Long chatId, Long userId, Integer type);

    ConversationVo info(Long id, Integer type);

    List<ConversationVo> getConversationList();

    ConversationMessageVo getConversationAndMessageList(Long lastTime);

    ConversationMessage addMessage(Long sendUserId, Integer platformType, SendMessageDto msg);

    ConversationVo createConversation(RqtStrIdsBean ids);

    List<MessageVo> msgList(Long cvId, Integer type, Long lastTime);
}
