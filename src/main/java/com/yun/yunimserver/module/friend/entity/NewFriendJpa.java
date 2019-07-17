package com.yun.yunimserver.module.friend.entity;

import com.yun.base.jpa.Repository.BaseJpaRepositoryByAutoId;

/**
 * @author: yun
 * @createdOn: 2019-07-17 15:21.
 */

public interface NewFriendJpa extends BaseJpaRepositoryByAutoId<NewFriend> {
    boolean existsByUserFriendId(String ufId);

    NewFriend findFirstById(Long id);
}
