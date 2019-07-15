package com.yun.yunimserver.module.imservice.service;

import com.yun.yunimserver.module.ClientPlatformType;
import com.yun.yunimserver.module.imservice.dtovo.PushDataDto;
import com.yun.yunimserver.module.imservice.entity.ConversationType;
import com.yun.yunimserver.wsapi.DtoVo.IgnoreUserPlatformDto;

import java.util.List;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2018-12-06 14:59.
 */

public interface PushService {
    void pushMessage(ConversationType type, Long cvId, Map data, List<IgnoreUserPlatformDto> ignoreList);

    void pushMessageIgnorePlatform(Long userId, Integer ignorePlatform, PushDataDto data);

    void pushMessage(Long userId, ClientPlatformType platform, PushDataDto data);
}
