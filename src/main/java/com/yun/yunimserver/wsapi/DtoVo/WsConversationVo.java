package com.yun.yunimserver.wsapi.DtoVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-15 13:27.
 */

@Data
@NoArgsConstructor
public class WsConversationVo {
    private Long id;

    // @JsonUnwrapped
    // private Conversation conversation;

    // private List<ConversationUserRl> userList;
}
