package com.yun.yunimserver.module.friend.service;

import com.querydsl.core.types.Projections;
import com.yun.base.module.Controller.RqtStrIdsBean;
import com.yun.yunimserver.module.BaseServiceImpl;
import com.yun.yunimserver.module.conversation.dtovo.ConversationVo;
import com.yun.yunimserver.module.conversation.service.ConversationService;
import com.yun.yunimserver.module.friend.dtovo.*;
import com.yun.yunimserver.module.friend.entity.NewFriend;
import com.yun.yunimserver.module.friend.entity.NewFriendJpa;
import com.yun.yunimserver.module.friend.entity.QNewFriend;
import com.yun.yunimserver.module.user.entity.QUser;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.service.UserService;
import com.yun.yunimserver.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-17 15:21.
 */

@Service
public class NewFriendServerImpl extends BaseServiceImpl {
    @Autowired
    private UserService userService;

    @Autowired
    private NewFriendJpa newFriendJpa;

    @Autowired
    private ConversationService conversationService;

    @Transactional
    public void addNewFriend(NewFriendDto dto) {
        User lgUser = RequestUtil.getLoginUser();

        User fUser = userService.getValidUser(dto.getFriendId());

        NewFriend newFriend = new NewFriend(lgUser, dto);

        if (newFriendJpa.existsByUserFriendId(newFriend.getUserFriendId())) {
            throwCommonError("已经添加过该好友了");
        }

        // 消息推送
        // todo

        // 保存
        newFriendJpa.save(newFriend);
    }

    @Transactional
    public void checkNewFriend(NewFriendCheckDto dto) {
        NewFriend newFriend = getAndCheckById(dto.getNewFriendId());

        User lgUser = RequestUtil.getLoginUser();

        if (!newFriend.getFriendId().equals(lgUser.getId())) {
            throwCommonError("你无权限处理");
        }

        if (!UserStatusType.WaitCheck.isEqualToInt(newFriend.getUserStatus())) {
            throwCommonError("请求已处理，请查看详情");
        }

        newFriend.updateByCheck(dto);

        List<String> userIds = new ArrayList<>();
        userIds.add(newFriend.getUserId().toString());
        userIds.add(newFriend.getFriendId().toString());
        RqtStrIdsBean ids = new RqtStrIdsBean();
        ids.setItems(userIds);

        ConversationVo vo = conversationService.createConversation(ids);
        newFriend.setConversationId(vo.getId());

        // 消息推送
        // todo

        // 保存
        newFriendJpa.save(newFriend);
    }

    public List<NewFriendVo> newFriendVos() {
        User lgUser = RequestUtil.getLoginUser();

        QNewFriend qNf = QNewFriend.newFriend;
        QUser qU = new QUser("qU");
        QUser qF = new QUser("qF");

        List<NewFriendVo> vos = queryFactory.selectDistinct(
                Projections.constructor(NewFriendVo.class, qNf, qU, qF)).from(qNf)
                .where(qNf.userId.eq(lgUser.getId()).or(qNf.friendId.eq(lgUser.getId())))
                .leftJoin(qU)
                .on(qU.id.eq(qNf.userId))
                .leftJoin(qF)
                .on(qF.id.eq(qNf.friendId))
                .fetch();

        return vos;
    }

    public List<FriendVo> friendVos() {
        User lgUser = RequestUtil.getLoginUser();

        QNewFriend qNf = QNewFriend.newFriend;
        QUser qU = new QUser("qU");
        QUser qF = new QUser("qF");

        List<FriendVo> vos = queryFactory.selectDistinct(
                Projections.constructor(FriendVo.class, qNf, qU, qF)).from(qNf)
                .where(qNf.userStatus.eq(UserStatusType.AddSuc.getType()).and(
                        qNf.userId.eq(lgUser.getId()).or(qNf.friendId.eq(lgUser.getId()))
                ))
                .leftJoin(qU)
                .on(qU.id.eq(qNf.userId))
                .leftJoin(qF)
                .on(qF.id.eq(qNf.friendId))
                .fetch();

        return vos;
    }

    // region --private method

    private NewFriend getAndCheckById(Long id) {
        if (id == null) {
            throwCommonError("id 无效");
        }

        NewFriend newFriend = newFriendJpa.findFirstById(id);

        if (newFriend == null) {
            throwCommonError("新的朋不存在");
        }

        return newFriend;
    }

    // endregion
}
