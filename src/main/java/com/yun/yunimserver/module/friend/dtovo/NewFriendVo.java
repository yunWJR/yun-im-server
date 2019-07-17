package com.yun.yunimserver.module.friend.dtovo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.yun.yunimserver.module.friend.entity.NewFriend;
import com.yun.yunimserver.module.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yun
 * @createdOn: 2019-07-17 16:21.
 */

@Data
@NoArgsConstructor
public class NewFriendVo {
    @JsonUnwrapped
    private NewFriend newFriend;

    private String userName;

    private String friendName;

    public NewFriendVo(NewFriend nf, User user, User friend) {
        newFriend = nf;

        if (user != null) {
            userName = user.getDisplayName();
        }

        if (friend != null) {
            friendName = friend.getDisplayName();
        }
    }
}
