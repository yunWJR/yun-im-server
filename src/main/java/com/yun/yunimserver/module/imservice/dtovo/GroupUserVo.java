package com.yun.yunimserver.module.imservice.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.imservice.entity.group.ConversationGroupUserRl;
import com.yun.yunimserver.module.userservice.entity.User;
import com.yun.yunimserver.module.userservice.entity.UserInfo;
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

    @JsonUnwrapped // 打开包装
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
