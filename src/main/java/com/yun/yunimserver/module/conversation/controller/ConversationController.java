package com.yun.yunimserver.module.conversation.controller;

/**
 * @author: yun
 * @createdOn: 2018/8/6 10:09.
 */

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.base.module.Controller.RqtStrIdsBean;
import com.yun.yunimserver.module.conversation.dtovo.ConversationMessageVo;
import com.yun.yunimserver.module.conversation.dtovo.ConversationVo;
import com.yun.yunimserver.module.conversation.dtovo.MessageVo;
import com.yun.yunimserver.module.conversation.dtovo.SendMessageDto;
import com.yun.yunimserver.module.conversation.entity.ConversationMessage;
import com.yun.yunimserver.module.conversation.entity.ConversationType;
import com.yun.yunimserver.module.conversation.service.ConversationService;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.util.LongIdUtil;
import com.yun.yunimserver.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018/7/26 17:25.
 */
@RestController
@RequestMapping(value = "api/v1/conversation")
@Api(tags = "02-00-会话管理")
public class ConversationController {

    // region --Field

    private final ConversationService conversationService;

    // endregion

    // region --Constructor

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    // endregion

    // region --Public method

    @ApiOperation(value = "新建会话", notes = "")
    @PostMapping(value = {"/create"})
    public BaseRstBeanT<ConversationVo> create(@RequestBody RqtStrIdsBean ids) {
        return new BaseRstBeanT<>(conversationService.createConversation(ids));
    }

    @ApiOperation(value = "会话信息", notes = "")
    @GetMapping(value = {"/info/{id}/{type}"})
    public BaseRstBeanT<ConversationVo> info(
            @ApiParam("id") @PathVariable String id,
            @ApiParam(ConversationType.des) @PathVariable Integer type
    ) {
        Long cvId = LongIdUtil.getValidId(id);

        return new BaseRstBeanT<>(conversationService.info(cvId, type));
    }

    @ApiOperation(value = "会话的消息信息", notes = "")
    @GetMapping(value = {"/msgList/{id}/{type}/{lastTime}"})
    public BaseRstBeanT<List<MessageVo>> msgList(
            @ApiParam("id") @PathVariable String id,
            @ApiParam(ConversationType.des) @PathVariable Integer type,
            @ApiParam("id") @PathVariable Long lastTime
    ) {
        Long cvId = LongIdUtil.getValidId(id);

        return new BaseRstBeanT<>(conversationService.msgList(cvId, type, lastTime));
    }

    @ApiOperation(value = "会话与消息列表", notes = "")
    @RequestMapping(value = {"/conAndMsgList/{lastTime}"}, method = RequestMethod.GET)
    public BaseRstBeanT<ConversationMessageVo> msgList(
            @ApiParam("id") @PathVariable Long lastTime
    ) {
        ConversationMessageVo vo = conversationService.getConversationAndMessageList(lastTime);

        return new BaseRstBeanT<>(vo);
    }

    @ApiOperation(value = "会话列表", notes = "")
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public BaseRstBeanT<List<ConversationVo>> conversationList() {
        List<ConversationVo> conversationVos = conversationService.getConversationList();

        return new BaseRstBeanT<>(conversationVos);
    }

    @ApiOperation(value = "发送消息", notes = "")
    @PostMapping(value = {"/sendMessage"})
    public BaseRstBeanT<ConversationMessage> sendMessage(
            @RequestBody @NotNull SendMessageDto msg
    ) {
        User lgUser = RequestUtil.getLoginUser();

        Integer pType = RequestUtil.getDeviceType();

        // todo
        if (pType == null) {
            pType = 0;
        }

        ConversationMessage msgData = conversationService.addMessage(lgUser.getId(), pType, msg);

        return new BaseRstBeanT<>(msgData);
    }

    // endregion
}
