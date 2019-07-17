package com.yun.yunimserver.module.friend.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import com.yun.yunimserver.module.friend.entity.NewFriend;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.entity.UserInfo;
import com.yun.yunimserver.util.RequestUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-17 16:34.
 */

@Data
@NoArgsConstructor
public class FriendVo {
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @JsonUnwrapped
    private UserInfo userInfo;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long conversationId;

    public FriendVo(NewFriend nf, User user, User friend) {
        conversationId = nf.getConversationId();

        if (user != null) {
            updateUserInfo(user);
        }

        if (friend != null) {
            updateUserInfo(friend);
        }
    }

    private void updateUserInfo(User user) {
        User lgUser = RequestUtil.getLoginUser();
        if (lgUser.getId().equals(user.getId())) {
            return;
        }

        userId = user.getId();
        userInfo = user.getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
    }
}