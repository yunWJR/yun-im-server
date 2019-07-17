package com.yun.yunimserver.module.user.controller;

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.yunimserver.config.NoNeedAccessAuthentication;
import com.yun.yunimserver.module.user.dtovo.UserVo;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.entity.UserAcct;
import com.yun.yunimserver.module.user.entity.UserInfo;
import com.yun.yunimserver.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The itemType User controller.
 */
@RestController
@RequestMapping(value = "api/v1/user")
@Api(tags = "01-00-用户管理", description = "用户管理模块")
public class UserController {

    // region --Field

    private final UserService userSv;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new User controller.
     * @param userSv the user sv
     */
    // 构造函数注入，明确成员加载顺序，以及提前避免调用 null 对象
    @Autowired
    public UserController(UserService userSv) {
        this.userSv = userSv;
    }

    // endregion

    // region --Public method

    /**
     * Add object.
     * @param acct the acct
     * @return the object
     */
    @NoNeedAccessAuthentication
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @ApiOperation(value = "注册用户", notes = "")
    public BaseRstBeanT<User> register(
            @ApiParam("帐号") @RequestBody UserAcct acct,
            @ApiParam("nickName") @RequestParam @NotNull String nickName,
            @ApiParam("phone") @RequestParam @NotNull String phone) {
        User user = userSv.register(acct, nickName, phone);

        return new BaseRstBeanT<>(user);
    }

    /**
     * Login object.
     * @param name the name
     * @param pws  the pws
     * @return the object
     */
    @NoNeedAccessAuthentication
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    @ApiOperation(value = "登录", notes = "")
    public BaseRstBeanT<UserVo> login(
            @ApiParam("帐号") @RequestParam @NotNull String name,
            @ApiParam("密码") @RequestParam @NotNull String pws) {

        UserVo user = userSv.login(name, pws);

        return new BaseRstBeanT<>(user);
    }

    /**
     * Users object.
     * @return the object
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户", notes = "")
    public BaseRstBeanT<List<User>> users() {
        List<User> users = userSv.getAllUser();

        return new BaseRstBeanT<>(users);
    }

    /**
     * Update user object.
     * @param info the info
     * @return the object
     */
    @RequestMapping(value = {"/updateUserInfo"}, method = RequestMethod.POST)
    @ApiOperation(value = "更新用户信息", notes = "")
    public BaseRstBeanT<User> updateUserInfo(@ApiParam("用户数据数据") @RequestBody UserInfo info) {
        User user = userSv.updateUserInfo(info);

        return new BaseRstBeanT<>(user);
    }

    /**
     * Update user object.
     * @return the object
     */
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", notes = "")
    public BaseRstBeanT<User> getUserInfo() {
        User user = userSv.getUserInfo();

        return new BaseRstBeanT<>(user);
    }

    // endregion
}