package com.yun.yunimserver.module.imservice.service;

import com.yun.base.Util.JrpUtil;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.base.token.AuthTokenPayload;
import com.yun.base.token.AuthTokenUtil;
import com.yun.yunimserver.module.BaseServiceImpl;
import com.yun.yunimserver.module.imservice.dtovo.ConversationVo;
import com.yun.yunimserver.module.imservice.dtovo.GroupUserVo;
import com.yun.yunimserver.module.imservice.dtovo.PushDataDto;
import com.yun.yunimserver.module.imservice.dtovo.SendMessageDto;
import com.yun.yunimserver.module.imservice.entity.ConversationMessage;
import com.yun.yunimserver.module.imservice.entity.ConversationMessageJpa;
import com.yun.yunimserver.module.imservice.entity.ConversationType;
import com.yun.yunimserver.module.imservice.entity.couple.ConversationCouple;
import com.yun.yunimserver.module.imservice.entity.couple.ConversationCoupleJpa;
import com.yun.yunimserver.module.imservice.entity.couple.QConversationCouple;
import com.yun.yunimserver.module.imservice.entity.usermessage.UserMessage;
import com.yun.yunimserver.module.imservice.entity.usermessage.UserMessageJpa;
import com.yun.yunimserver.module.userservice.entity.QUser;
import com.yun.yunimserver.module.userservice.entity.User;
import com.yun.yunimserver.module.userservice.entity.UserJpa;
import com.yun.yunimserver.module.userservice.limit.UserUtil;
import com.yun.yunimserver.module.userservice.service.UserServiceImpl;
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
    private UserUtil userUtil;

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

        WsConversationDto wsDto = new WsConversationDto();
        wsDto.setClientGroupId(newCp.getId().toString());
        wsDto.setClientUserId(new ArrayList<>());
        wsDto.getClientUserId().add(newCp.getInfo().getCreateUserId().toString());
        wsDto.getClientUserId().add(newCp.getInfo().getAnotherUserId().toString());
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

        PushDataDto pushDataDto = new PushDataDto(msg);

        // 发送者其他平台推送
        // pushService.pushMessageIgnorePlatform(msg.getSendUserId(), platformType, pushDataDto);

        // 对方推送
        Long anotherUserId;
        if (cp.getInfo().getCreateUserId().equals(msg.getSendUserId())) {
            anotherUserId = cp.getInfo().getAnotherUserId();
        } else {
            anotherUserId = cp.getInfo().getCreateUserId();
        }
        // pushService.pushMessageIgnorePlatform(anotherUserId, ClientPlatformType.Unknown.getType(), pushDataDto);

        pushService.pushMessage(ConversationType.Couple, cp.getId(), msg.gMapValue(), null);  // todo

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
        AuthTokenPayload token = AuthTokenUtil.getThreadLocalToken();
        User user = userUtil.getUser(token);

        ConversationCouple cp = JrpUtil.findById(conversationCoupleJpa, coupleId);

        if (cp == null) {
            throw RstBeanException.RstComErrBeanWithStr("找不到对话");
        }

        Long userIdForInfo = null;
        if (user.getId().equals(cp.getInfo().getCreateUserId())) {
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
