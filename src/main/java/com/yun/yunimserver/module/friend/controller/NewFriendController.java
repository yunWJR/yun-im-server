package com.yun.yunimserver.module.friend.controller;

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.yunimserver.module.friend.dtovo.FriendVo;
import com.yun.yunimserver.module.friend.dtovo.NewFriendCheckDto;
import com.yun.yunimserver.module.friend.dtovo.NewFriendDto;
import com.yun.yunimserver.module.friend.dtovo.NewFriendVo;
import com.yun.yunimserver.module.friend.service.NewFriendServerImpl;
import com.yun.yunimserver.module.user.dtovo.UserVo;
import com.yun.yunimserver.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-17 16:43.
 */

@RestController
@RequestMapping(value = "api/v1/newFriend")
@Api(tags = "03-00-新的朋友")
public class NewFriendController {
    @Autowired
    private NewFriendServerImpl newFriendServer;

    @Autowired
    private UserService userSv;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户", notes = "")
    public BaseRstBeanT<List<UserVo>> users() {
        List<UserVo> users = userSv.getAllUser();

        return new BaseRstBeanT<>(users);
    }

    @ApiOperation(value = "添加朋友", notes = "")
    @PostMapping(value = {"/addNewFriend"})
    public BaseRstBeanT<String> addNewFriend(@RequestBody NewFriendDto dto) {
        newFriendServer.addNewFriend(dto);

        return new BaseRstBeanT<>("添加成功");
    }

    @ApiOperation(value = "审核朋友", notes = "")
    @PostMapping(value = {"/checkNewFriend"})
    public BaseRstBeanT<String> checkNewFriend(@RequestBody NewFriendCheckDto dto) {
        newFriendServer.checkNewFriend(dto);

        return new BaseRstBeanT<>("审核成功");
    }

    @ApiOperation(value = "新的朋友列表", notes = "")
    @GetMapping(value = {"/newFriend"})
    public BaseRstBeanT<List<NewFriendVo>> newFriendVos() {
        List<NewFriendVo> vos = newFriendServer.newFriendVos();

        return new BaseRstBeanT<>(vos);
    }

    @ApiOperation(value = "朋友列表", notes = "")
    @GetMapping(value = {"/friend"})
    public BaseRstBeanT<List<FriendVo>> friendVos() {
        List<FriendVo> vos = newFriendServer.friendVos();

        return new BaseRstBeanT<>(vos);
    }
}
