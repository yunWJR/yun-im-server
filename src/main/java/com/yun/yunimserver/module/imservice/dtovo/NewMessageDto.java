package com.yun.yunimserver.module.imservice.dtovo;

import lombok.Data;

/**
 * @author: yun
 * @createdOn: 2018/8/7 09:34.
 */

@Data
public class NewMessageDto {

    // region --Field

    private ConversationVo messageItem;

    // endregion

    // region --Constructor

    public NewMessageDto() {
    }

    public NewMessageDto(ConversationVo messageItem) {
        this.messageItem = messageItem;
    }

    // endregion
}
