package com.yun.yunimserver.module.imservice.service;

import com.yun.yunimserver.module.imservice.dtovo.ConversationVo;
import com.yun.yunimserver.module.imservice.dtovo.GroupInfoVo;
import com.yun.yunimserver.module.imservice.dtovo.SendMessageDto;
import com.yun.yunimserver.module.imservice.entity.ConversationMessage;
import com.yun.yunimserver.module.imservice.entity.group.ConversationGroupUserRl;
import com.yun.yunimserver.module.userservice.entity.User;

import java.util.HashSet;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018/7/26 09:36.
 */

public interface GroupService {
    ConversationVo createGroupChat(HashSet<Long> userIdsPara);

    ConversationVo conversationInfo(Long id);

    List<ConversationGroupUserRl> addUserToGroup(Long groupId, List<Long> userIds);

    List<Long> getUserIdsInChat(Long chatId);

    List<GroupInfoVo> getChatGroupUserList(Long chatGroupId);

    ConversationMessage addMessage(User sUser, Integer platformType, SendMessageDto msg);

    GroupInfoVo info(Long chatGroupId);
}
