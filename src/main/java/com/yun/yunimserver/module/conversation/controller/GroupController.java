package com.yun.yunimserver.module.conversation.controller;

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.base.module.Controller.RqtStrIdsBean;
import com.yun.yunimserver.module.conversation.dtovo.GroupInfoVo;
import com.yun.yunimserver.module.conversation.entity.group.ConversationGroupUserRl;
import com.yun.yunimserver.module.conversation.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2018/7/26 17:25.
 */
@RestController
@RequestMapping(value = "api/v1/conversation/group")
@Api(tags = "02-02-会话管理-群聊")
public class GroupController {

    // region --Field

    private GroupService groupService;

    // endregion

    // region --Constructor

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // endregion

    // region --Public method

    @ApiOperation(value = "群聊信息", notes = "")
    @GetMapping(value = {"/info/{chatGroupId}"})
    public BaseRstBeanT<GroupInfoVo> info(@PathVariable Long chatGroupId) {
        return new BaseRstBeanT<>(groupService.info(chatGroupId));
    }

    @ApiOperation(value = "添加成员", notes = "")
    @RequestMapping(value = {"/addUser/{chatGroupId}"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "groupId", required = true, dataType = "string"),
            @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "RqtStrIdsBean"),
    })
    public BaseRstBeanT<List<ConversationGroupUserRl>> addUser(@PathVariable Long chatGroupId, @RequestBody RqtStrIdsBean ids) {
        List<Long> userIds = new ArrayList<>();
        for (String id : ids.getItems()) {
            userIds.add(Long.valueOf(id));
        }

        return new BaseRstBeanT<>(groupService.addUserToGroup(chatGroupId, userIds));
    }

    @ApiOperation(value = "群聊的成员信息", notes = "")
    @RequestMapping(value = {"/mebList/{chatGroupId}"}, method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "groupId", required = true, dataType = "string"),
    })
    public BaseRstBeanT<List<GroupInfoVo>> mebListInChatGroup(
            @PathVariable @NotNull Long chatGroupId,
            @RequestParam Long lastUpdateTime) {
        return new BaseRstBeanT<>(groupService.getChatGroupUserList(chatGroupId));
    }

    @ApiOperation(value = "所有群聊的成员信息", notes = "")
    @RequestMapping(value = {"/mebList"}, method = RequestMethod.GET)
    @ApiImplicitParams({
    })
    public BaseRstBeanT<List<GroupInfoVo>> mebListInAllChatGroup() {
        return new BaseRstBeanT<>(groupService.getChatGroupUserList(null));
    }

    // endregion
}
