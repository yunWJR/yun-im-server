package com.yun.yunimserver.module.conversation.service;

import com.yun.base.Util.JrpUtil;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.yunimserver.module.BaseServiceImpl;
import com.yun.yunimserver.module.conversation.dtovo.ConversationVo;
import com.yun.yunimserver.module.conversation.dtovo.GroupUserVo;
import com.yun.yunimserver.module.conversation.dtovo.SendMessageDto;
import com.yun.yunimserver.module.conversation.entity.ConversationMessage;
import com.yun.yunimserver.module.conversation.entity.ConversationMessageJpa;
import com.yun.yunimserver.module.conversation.entity.ConversationType;
import com.yun.yunimserver.module.conversation.entity.couple.ConversationCouple;
import com.yun.yunimserver.module.conversation.entity.couple.ConversationCoupleJpa;
import com.yun.yunimserver.module.conversation.entity.couple.QConversationCouple;
import com.yun.yunimserver.module.conversation.entity.usermessage.UserMessage;
import com.yun.yunimserver.module.conversation.entity.usermessage.UserMessageJpa;
import com.yun.yunimserver.module.push.service.PushService;
import com.yun.yunimserver.module.user.entity.QUser;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.entity.UserJpa;
import com.yun.yunimserver.module.user.service.UserServiceImpl;
import com.yun.yunimserver.util.RequestUtil;
import com.yun.yunimserver.wsapi.DtoVo.WsConversationDto;
import com.yun.yunimserver.wsapi.DtoVo.WsConversationVo;
import com.yun.yunimserver.wsapi.WsApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018-12-03 15:47.
 */

@Service
public class CoupleServiceImpl extends BaseServiceImpl implements CoupleService {

    // region --Field

    @Autowired
    WsApiServiceImpl wsApiService;

    @Autowired
    private ConversationCoupleJpa conversationCoupleJpa;

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ConversationMessageJpa conversationMessageJpa;

    @Autowired
    private UserMessageJpa userMessageJpa;

    @Autowired
    private PushService pushService;

    // endregion

    // region --Constructor

    // endregion

    // region --Public method

    @Override
    @Transactional()
    public ConversationVo createCouple(Long tgUserId) {
        User tgUser = userServiceImpl.getValidUser(tgUserId);

        User lgUser = RequestUtil.getLoginUser();

        QConversationCouple couple = QConversationCouple.conversationCouple;
        ConversationCouple cCouple = queryFactory.selectFrom(couple)
                .where((couple.info.createUserId.eq(tgUser.getId()).and(couple.info.anotherUserId.eq(lgUser.getId())))
                        .or
                                (couple.info.createUserId.eq(lgUser.getId()).and(couple.info.anotherUserId.eq(tgUser.getId())))
                ).fetchFirst();

        // 存在对话
        if (cCouple != null) {
            return new ConversationVo(cCouple, tgUser);
        }

        ConversationCouple newCp = new ConversationCouple(lgUser.getId(), tgUserId);
        conversationCoupleJpa.save(newCp);

        List<String> extraUserId = new ArrayList<>();
        extraUserId.add(newCp.getInfo().getCreateUserId().toString());
        extraUserId.add(newCp.getInfo().getAnotherUserId().toString());

        WsConversationDto wsDto = WsConversationDto.newItem(ConversationType.Couple, newCp.getId(), extraUserId);
        WsConversationVo vo = wsApiService.createConversation(wsDto);

        return new ConversationVo(newCp, tgUser);
    }

    @Override
    public ConversationVo conversationInfo(Long id) {
        QConversationCouple couple = QConversationCouple.conversationCouple;
        ConversationCouple cp = queryFactory.selectFrom(couple)
                .where(couple.id.eq(id))
                .fetchFirst();

        Long tgUserId = cp.getInfo().getCreateUserId();
        User lgUser = RequestUtil.getLoginUser();
        if (tgUserId.equals(lgUser.getId())) {
            tgUserId = cp.getInfo().getAnotherUserId();
        }

        QUser qUser = QUser.user;

        User tgUser = queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(tgUserId))
                .fetchFirst();

        return new ConversationVo(cp, tgUser);
    }

    @Override
    @Transactional()
    public ConversationMessage addMessage(User sUser, Integer platformType, SendMessageDto msgDto) {
        ConversationCouple cp = JrpUtil.findById(conversationCoupleJpa, msgDto.getInfo().getConversationId());
        if (cp == null) {
            throw RstBeanException.RstComErrBeanWithStr("对话不存在");
        }

        ConversationMessage msg = new ConversationMessage(sUser.getId(), platformType, msgDto);
        conversationMessageJpa.save(msg);

        // 用户消息
        List<UserMessage> userMessages = new ArrayList<>();
        userMessages.add(new UserMessage(cp.getInfo().getCreateUserId(), msg));
        userMessages.add(new UserMessage(cp.getInfo().getAnotherUserId(), msg));
        userMessageJpa.saveAll(userMessages);

        // todo
        pushService.pushMessage(ConversationType.Couple, cp.getId(), msg.gJsonValue(), null);

        return msg;
    }

    @Override
    public List<Long> getUserIdsInCouple(Long coupleId) {
        ConversationCouple cp = JrpUtil.findById(conversationCoupleJpa, coupleId);

        if (cp == null) {
            throw RstBeanException.RstComErrBeanWithStr("找不到对话");
        }

        List<Long> userIds = new ArrayList<>();

        userIds.add(cp.getInfo().getAnotherUserId());
        userIds.add(cp.getInfo().getCreateUserId());

        return userIds;
    }

    @Override
    public GroupUserVo getChatCoupleUser(Long coupleId) {
        User lgUser = RequestUtil.getLoginUser();

        ConversationCouple cp = JrpUtil.findById(conversationCoupleJpa, coupleId);

        if (cp == null) {
            throw RstBeanException.RstComErrBeanWithStr("找不到对话");
        }

        Long userIdForInfo = null;
        if (lgUser.getId().equals(cp.getInfo().getCreateUserId())) {
            userIdForInfo = cp.getInfo().getAnotherUserId();
        } else {
            userIdForInfo = cp.getInfo().getCreateUserId();
        }

        User userTmp = JrpUtil.findById(userJpa, userIdForInfo);
        if (userTmp == null) {
            throw RstBeanException.RstComErrBeanWithStr("获取用户信息失败");
        }

        GroupUserVo vo = new GroupUserVo(userTmp, null);

        return vo;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
