package com.yun.yunimserver.module.push.service;

import com.yun.yunimserver.module.conversation.entity.ConversationType;
import com.yun.yunimserver.wsapi.DtoVo.IgnoreUserPlatformDto;
import com.yun.yunimserver.wsapi.DtoVo.MessageDto;
import com.yun.yunimserver.wsapi.WsApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018-12-06 14:59.
 */

@Service
public class PushServiceImpl implements PushService {

    // region --Field

    @Autowired
    WsApiServiceImpl wsApiService;

    @Override
    public void pushMessage(ConversationType type, Long cvId, String data, List<IgnoreUserPlatformDto> ignoreList) {
        MessageDto dto = MessageDto.newItem(type, cvId, data, ignoreList);

        wsApiService.pushMessage(dto);
    }
}
