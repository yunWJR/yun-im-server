package com.yun.yunimserver.wsapi.DtoVo;

import com.yun.yunimserver.module.conversation.entity.ConversationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-15 13:30.
 */

@Data
@NoArgsConstructor
public class MessageDto {
    private Long conversationId;

    private String extraConversationId;

    private String contentJson;

    private String contentString;

    private List<IgnoreUserPlatformDto> ignoreList;

    public static MessageDto newItem(ConversationType type, Long cvId, String data, List<IgnoreUserPlatformDto> ignoreList) {
        MessageDto dto = new MessageDto();

        String extraId = String.format("%s_%s", type.getType(), cvId);
        dto.setExtraConversationId(extraId);

        dto.setContentJson(data);
        dto.setIgnoreList(ignoreList);

        return dto;
    }
}
