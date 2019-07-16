package com.yun.yunimserver.module.imservice.service;

import com.yun.yunimserver.module.ClientPlatformType;
import com.yun.yunimserver.module.imservice.dtovo.PushDataDto;
import com.yun.yunimserver.module.imservice.entity.ConversationType;
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

    @Override
    public void pushMessageIgnorePlatform(Long userId, Integer ignorePlatform, PushDataDto data) {
        for (int i = ClientPlatformType.Min.getType(); i < ClientPlatformType.Max.getType(); i++) {
            if (ignorePlatform != null && ignorePlatform.equals(i)) {
                continue;
            }

            pushMessage(userId, ClientPlatformType.valueOfInt(i), data);
        }
    }

    @Override
    public void pushMessage(Long userId, ClientPlatformType platform, PushDataDto data) {
        if (!ClientPlatformType.isValidType(platform)) {
            return;
        }

        // boolean wsPush = imWebSocketService.pushMessage(userId, platform, new WsRspMessage(data));
        // if (wsPush) {
        //     return;
        // }

        // todo 其他推送
    }
}
