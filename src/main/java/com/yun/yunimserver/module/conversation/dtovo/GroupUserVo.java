package com.yun.yunimserver.module.conversation.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.conversation.entity.group.ConversationGroupUserRl;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-08 11:34.
 */

@Data
@NoArgsConstructor
public class GroupUserVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @JsonUnwrapped
    private UserInfo userInfo;

    private String remarkName;

    private Integer role = 0;

    public GroupUserVo(User user, ConversationGroupUserRl rl) {
        if (user != null) {
            this.id = user.getId();
            this.userInfo = user.getUserInfo();
        }

        if (rl != null) {
            this.remarkName = rl.getRemarkName();
            this.role = rl.getRole();
        }
    }
}
