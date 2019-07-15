package com.yun.yunimserver.module.imservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.yun.base.Util.JrpUtil;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.base.token.AuthTokenPayload;
import com.yun.base.token.AuthTokenUtil;
import com.yun.yunimserver.module.BaseServiceImpl;
import com.yun.yunimserver.module.imservice.dtovo.*;
import com.yun.yunimserver.module.imservice.entity.ConversationMessage;
import com.yun.yunimserver.module.imservice.entity.ConversationMessageJpa;
import com.yun.yunimserver.module.imservice.entity.ConversationType;
import com.yun.yunimserver.module.imservice.entity.group.*;
import com.yun.yunimserver.module.imservice.entity.usermessage.UserMessage;
import com.yun.yunimserver.module.imservice.entity.usermessage.UserMessageJpa;
import com.yun.yunimserver.module.userservice.entity.QUser;
import com.yun.yunimserver.module.userservice.entity.User;
import com.yun.yunimserver.module.userservice.entity.UserJpa;
import com.yun.yunimserver.util.RequestUtil;
import com.yun.yunimserver.wsapi.DtoVo.WsConversationDto;
import com.yun.yunimserver.wsapi.DtoVo.WsConversationVo;
import com.yun.yunimserver.wsapi.WsApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018/7/26 09:36.
 */

@Service
public class GroupServiceImpl extends BaseServiceImpl implements GroupService {

    // region --Field

    @Autowired
    WsApiServiceImpl wsApiService;
    @Autowired
    private ConversationGroupJpa conversationGroupJpa;
    @Autowired
    private ConversationGroupUserRlJpa conversationGroupUserRlJpa;
    @Autowired
    private ConversationMessageJpa conversationMessageJpa;
    @Autowired
    private UserMessageJpa userMessageJpa;
    @Autowired
    private PushService pushService;
    @Autowired
    private UserJpa userJpa;

    // endregion

    // region --Constructor

    // endregion

    // region --Public method

    @Override
    @Transactional
    public ConversationVo createGroupChat(HashSet<Long> userIds) {
        AuthTokenPayload token = AuthTokenUtil.getThreadLocalToken();
        Long userId = Long.valueOf(token.getUserId());

        if (userIds.size() < 3) {
            throwCommonError("人数不够，无法创建群");
        }

        // todo 人数判断
        if (userIds.size() > 200) {
            throw RstBeanException.RstComErrBeanWithStr("人数过多");
        }

        // 用户是否存在判断
        QUser qUser = QUser.user;
        long userCount = queryFactory.selectFrom(qUser)
                .where(qUser.id.in(userIds))
                .fetchCount();
        if (userCount != userIds.size()) {
            throwCommonError("用户信息错误");
        }

        ConversationGroup gp = new ConversationGroup(userId);
        conversationGroupJpa.save(gp);

        List<ConversationGroupUserRl> userRlList = new ArrayList<>();

        List<String> clientUserIds = new ArrayList<>();

        for (Long uId : userIds) {
            userRlList.add(new ConversationGroupUserRl(gp.getId(), uId));

            clientUserIds.add(uId.toString());
        }
        conversationGroupUserRlJpa.saveAll(userRlList);

        ConversationVo cvVo = new ConversationVo(gp);

        WsConversationDto wsDto = new WsConversationDto();
        wsDto.setClientGroupId(gp.getId().toString());
        wsDto.setClientUserId(clientUserIds);
        WsConversationVo vo = wsApiService.createConversation(wsDto);

        return cvVo;
    }

    @Override
    public ConversationVo conversationInfo(Long id) {
        ConversationGroup group = getValidConversation(id);

        return new ConversationVo(group);
    }

    @Override
    @Transactional()
    public List<ConversationGroupUserRl> addUserToGroup(Long groupId, List<Long> userIds) {
        if (userIds.size() == 0) {
            throw RstBeanException.RstComErrBeanWithStr("无用户");
        }

        if (!conversationGroupJpa.existsById(groupId)) {
            throw RstBeanException.RstComErrBeanWithStr("chatGroupId参数错误");
        }

        for (Long userId : userIds) {
            if (!userJpa.existsById(userId)) {
                throw RstBeanException.RstComErrBeanWithStr("无用户");
            }
        }

        List<ConversationGroupUserRl> newUserList = new ArrayList<>();
        for (Long u : userIds) {
            QConversationGroupUserRl qRl = QConversationGroupUserRl.conversationGroupUserRl;
            ConversationGroupUserRl rl = queryFactory.selectFrom(qRl)
                    .where(qRl.pkId.groupId.eq(groupId).and(qRl.pkId.userId.eq(u)))
                    .fetchFirst();

            if (rl == null) {
                newUserList.add(new ConversationGroupUserRl(groupId, u));
            }
        }

        newUserList = conversationGroupUserRlJpa.saveAll(newUserList);

        return newUserList;
    }

    @Override
    @Transactional()
    public ConversationMessage addMessage(User sUser, Integer platformType, SendMessageDto msgDto) {
        ConversationGroup group = getValidConversation(msgDto.getInfo().getConversationId());

        ConversationMessage msg = new ConversationMessage(sUser.getId(), platformType, msgDto);
        conversationMessageJpa.save(msg);

        List<Long> groupUserIds = getUserIdsInChat(group.getId());

        // 用户消息
        List<UserMessage> userMessages = new ArrayList<>();
        for (Long userId : groupUserIds) {
            userMessages.add(new UserMessage(userId, msg));
        }
        userMessageJpa.saveAll(userMessages);

        PushDataDto pushDataDto = new PushDataDto(msg);

        pushService.pushMessage(ConversationType.Group, group.getId(), msg.gJsonValue(), null);  // todo

        // for (Long userId : groupUserIds) {
        //     if (userId.equals(sUser.getId())) {
        //         // 发送者其他平台推送
        //         pushService.pushMessageIgnorePlatform(userId, platformType, pushDataDto);
        //     } else {
        //         pushService.pushMessageIgnorePlatform(userId, ClientPlatformType.Unknown.getType(), pushDataDto);
        //     }
        // }

        return msg;
    }

    @Override
    public List<Long> getUserIdsInChat(Long chatId) {

        QConversationGroupUserRl qRl = QConversationGroupUserRl.conversationGroupUserRl;
        List<Long> list = queryFactory.select(qRl.pkId.userId)
                .from(qRl)
                .where(qRl.pkId.groupId.eq(chatId))
                .fetch();

        return list;
    }

    @Override
    public List<GroupInfoVo> getChatGroupUserList(Long chatGroupId) {
        User lgUser = RequestUtil.getLoginUser();

        QConversationGroupUserRl qRl = QConversationGroupUserRl.conversationGroupUserRl;

        BooleanBuilder bb = new BooleanBuilder();
        bb.and(qRl.pkId.userId.eq(lgUser.getId()));
        if (chatGroupId != null) {
            bb.and(qRl.pkId.groupId.eq(chatGroupId));
        }

        List<ConversationGroupUserRl> gpUserList = queryFactory.selectFrom(qRl)
                .where(bb)
                .fetch();

        List<GroupInfoVo> dgUserInfoList = new ArrayList<>();
        for (ConversationGroupUserRl dgUser : gpUserList) {
            ConversationGroup dg = JrpUtil.findById(conversationGroupJpa, dgUser.getPkId().getGroupId());

            GroupInfoVo info = info(dgUser.getPkId().getGroupId());

            dgUserInfoList.add(info);
        }

        return dgUserInfoList;
    }

    @Override
    public GroupInfoVo info(Long chatGroupId) {
        ConversationGroup group = getValidConversation(chatGroupId);

        GroupInfoVo vo = new GroupInfoVo(group);

        QUser qUser = QUser.user;
        QConversationGroupUserRl qGUserRl = QConversationGroupUserRl.conversationGroupUserRl;
        List<GroupUserVo> userVos = queryFactory.select(Projections.constructor(GroupUserVo.class, qUser, qGUserRl)).from(qGUserRl)
                .where(qGUserRl.pkId.groupId.eq(chatGroupId))
                .innerJoin(qUser)
                .on(qUser.id.eq(qGUserRl.pkId.userId))
                .fetch();

        vo.setUserList(userVos);

        return vo;
    }

    // endregion

    // region --private method

    public ConversationGroup getValidConversation(Long id) {
        QConversationGroup qGroup = QConversationGroup.conversationGroup;

        ConversationGroup group = queryFactory.selectFrom(qGroup)
                .where(qGroup.id.eq(id))
                .fetchFirst();

        if (group == null) {
            throwCommonError("对话不存在");
        }

        return group;
    }

    // endregion

    // region --Other

    // endregion
}
