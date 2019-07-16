package com.yun.yunimserver.module.imservice.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.yun.base.module.Controller.RqtStrIdsBean;
import com.yun.yunimserver.module.BaseServiceImpl;
import com.yun.yunimserver.module.imservice.dtovo.ConversationMessageVo;
import com.yun.yunimserver.module.imservice.dtovo.ConversationVo;
import com.yun.yunimserver.module.imservice.dtovo.MessageVo;
import com.yun.yunimserver.module.imservice.dtovo.SendMessageDto;
import com.yun.yunimserver.module.imservice.entity.ConversationMessage;
import com.yun.yunimserver.module.imservice.entity.ConversationType;
import com.yun.yunimserver.module.imservice.entity.couple.QConversationCouple;
import com.yun.yunimserver.module.imservice.entity.group.QConversationGroup;
import com.yun.yunimserver.module.imservice.entity.group.QConversationGroupUserRl;
import com.yun.yunimserver.module.imservice.entity.usermessage.QUserMessage;
import com.yun.yunimserver.module.userservice.entity.QUser;
import com.yun.yunimserver.module.userservice.entity.User;
import com.yun.yunimserver.util.LongIdUtil;
import com.yun.yunimserver.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: yun
 * @createdOn: 2018/8/3 15:43.
 */

@Service
public class ConversationServiceImpl extends BaseServiceImpl implements ConversationService {

    // region --Field

    @Autowired
    private CoupleService coupleService;

    @Autowired
    private GroupService groupService;

    // endregion

    // region --Constructor

    // endregion

    // region --Public method

    @Override
    @Transactional()
    public ConversationVo createConversation(RqtStrIdsBean ids) {
        HashSet<Long> userIds = new HashSet<>();
        for (String id : ids.getItems()) {
            userIds.add(LongIdUtil.getValidId(id));
        }

        User lgUser = RequestUtil.getLoginUser();
        userIds.add(lgUser.getId());

        if (userIds.size() < 2) {
            throwCommonError("用户人数不够，无法创建会话");
        } else if (userIds.size() == 2) {
            Long tgUserId = null;
            for (Long uId : userIds) {
                if (!uId.equals(lgUser.getId())) {
                    tgUserId = uId;
                }
            }
            return coupleService.createCouple(tgUserId);
        } else {
            return groupService.createGroupChat(userIds);
        }

        return null;
    }

    @Override
    public List<MessageVo> msgList(Long cvId, Integer type, Long lastTime) {
        if (cvId == null || type == null) {
            throwCommonError("参数错误");
        }

        User lgUser = RequestUtil.getLoginUser();

        QUserMessage qUserMsg = QUserMessage.userMessage;
        BooleanBuilder bb = new BooleanBuilder();

        bb.and(qUserMsg.userId.eq(lgUser.getId()));
        if (lastTime != null) {
            bb.and(qUserMsg.messageCreateTime.gt(lastTime));
        }

        bb.and(qUserMsg.info.conversationId.eq(cvId));
        bb.and(qUserMsg.info.conversationType.eq(type));

        List<MessageVo> userMsgList = queryFactory.select(Projections.constructor(MessageVo.class, qUserMsg))
                .from(qUserMsg)
                .where(bb)
                .orderBy(qUserMsg.messageCreateTime.desc())
                .fetch();

        Collections.reverse(userMsgList);

        return userMsgList;
    }

    @Override
    public ConversationVo info(Long id, Integer type) {
        if (ConversationType.Couple.isEquelTo(type)) {
            return coupleService.conversationInfo(id);
        } else if (ConversationType.Group.isEquelTo(type)) {
            return groupService.conversationInfo(id);
        } else {
            throwCommonError("类型无效");
        }

        return null;
    }

    @Override
    public List<Long> getUserIdsImMessage(Long chatId, Long userId, Integer type) {
        if (ConversationType.Couple.isEquelTo(type)) {
            List<Long> list = coupleService.getUserIdsInCouple(chatId);
            return list;
        }

        if (ConversationType.Group.isEquelTo(type)) {
            return groupService.getUserIdsInChat(chatId);
        }

        throwCommonError("类型不正确");

        return new ArrayList<>();
    }

    @Override
    public List<ConversationVo> getConversationList() {
        User lgUser = RequestUtil.getLoginUser();

        List<ConversationVo> dtoList = new ArrayList<>();

        QConversationCouple qCouple = QConversationCouple.conversationCouple;
        List<Long> coupleIds = queryFactory.select(qCouple.id)
                .from(qCouple)
                .where(qCouple.info.createUserId.eq(lgUser.getId()).or(qCouple.info.anotherUserId.eq(lgUser.getId())))
                .fetch();

        for (Long cId : coupleIds) {
            dtoList.add(info(cId, ConversationType.Couple.getType()));
        }

        QConversationGroup qGroup = QConversationGroup.conversationGroup;
        QConversationGroupUserRl qRl = QConversationGroupUserRl.conversationGroupUserRl;
        List<Long> groupIds = queryFactory.select(qGroup.id)
                .from(qGroup)
                .innerJoin(qRl)
                .on(qRl.pkId.groupId.eq(qGroup.id).and(qRl.pkId.userId.eq(lgUser.getId())))
                .fetch();

        for (Long gId : groupIds) {
            dtoList.add(info(gId, ConversationType.Group.getType()));
        }

        return dtoList;
    }

    @Override
    public ConversationMessageVo getConversationAndMessageList(Long lastTime) {
        User lgUser = RequestUtil.getLoginUser();

        QUserMessage qUserMsg = QUserMessage.userMessage;
        BooleanBuilder bb = new BooleanBuilder();

        bb.and(qUserMsg.userId.eq(lgUser.getId()));
        if (lastTime != null) {
            bb.and(qUserMsg.messageCreateTime.gt(lastTime));
        }

        List<MessageVo> userMsgList = queryFactory.select(Projections.constructor(MessageVo.class, qUserMsg))
                .from(qUserMsg)
                .where(bb)
                .orderBy(qUserMsg.messageCreateTime.desc())
                .fetch();

        ConversationMessageVo vo = new ConversationMessageVo();
        if (userMsgList.size() > 0) {
            vo.setLastTime(userMsgList.get(0).getCreateTime());
        } else {
            vo.setLastTime(lastTime == null ? 0 : lastTime);
        }

        // 按ConversationId分组
        Map<Long, List<MessageVo>> cvMsgMap = userMsgList.stream().collect(Collectors.groupingBy(MessageVo::getConversationId));

        List<ConversationVo> cvList = new ArrayList<>();
        for (Long cvId : cvMsgMap.keySet()) {
            List<MessageVo> cvMsg = cvMsgMap.get(cvId);

            if (cvMsg.size() > 0) {
                ConversationVo cVo = info(cvId, cvMsg.get(0).getInfo().getConversationType());

                Collections.reverse(cvMsg);

                cVo.setMessageList(cvMsg);

                cvList.add(cVo);
            }
        }

        cvList = cvList.stream().sorted(Comparator.comparing(ConversationVo::getLastMsgTime)).collect(Collectors.toList());

        vo.setConversationList(cvList);

        return vo;
    }

    @Override
    @Transactional()
    public ConversationMessage addMessage(Long sendUserId, Integer platformType, SendMessageDto msg) {
        QUser qUser = QUser.user;

        User sUser = queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(sendUserId)).fetchFirst();
        if (sUser == null) {
            throwCommonError("发送用户无效");
        }

        // todo
        if (ConversationType.Couple.isEquelTo(msg.getInfo().getConversationType())) {
            return coupleService.addMessage(sUser, platformType, msg);
        } else if (ConversationType.Group.isEquelTo(msg.getInfo().getConversationType())) {
            return groupService.addMessage(sUser, platformType, msg);
        } else {
            throwCommonError("消息类型无效");
        }

        return null;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
