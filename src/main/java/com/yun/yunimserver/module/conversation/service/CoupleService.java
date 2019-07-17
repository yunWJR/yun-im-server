package com.yun.yunimserver.module.conversation.service;

import com.yun.yunimserver.module.conversation.dtovo.ConversationVo;
import com.yun.yunimserver.module.conversation.dtovo.GroupUserVo;
import com.yun.yunimserver.module.conversation.dtovo.SendMessageDto;
import com.yun.yunimserver.module.conversation.entity.ConversationMessage;
import com.yun.yunimserver.module.user.entity.User;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018-12-03 15:47.
 */

public interface CoupleService {
    ConversationVo createCouple(Long tgUserId);

    ConversationVo conversationInfo(Long id);

    List<Long> getUserIdsInCouple(Long coupleId);

    GroupUserVo getChatCoupleUser(Long coupleId);

    ConversationMessage addMessage(User sUser, Integer platformType, SendMessageDto msg);
}
