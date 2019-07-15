package com.yun.yunimserver.module.imservice.service;

import com.yun.yunimserver.module.imservice.dtovo.ConversationVo;
import com.yun.yunimserver.module.imservice.dtovo.GroupUserVo;
import com.yun.yunimserver.module.imservice.dtovo.SendMessageDto;
import com.yun.yunimserver.module.imservice.entity.ConversationMessage;
import com.yun.yunimserver.module.userservice.entity.User;

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
