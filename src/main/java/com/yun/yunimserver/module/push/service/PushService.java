package com.yun.yunimserver.module.push.service;

import com.yun.yunimserver.module.conversation.entity.ConversationType;
import com.yun.yunimserver.wsapi.DtoVo.IgnoreUserPlatformDto;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018-12-06 14:59.
 */

public interface PushService {
    void pushMessage(ConversationType type, Long cvId, String data, List<IgnoreUserPlatformDto> ignoreList);
}
