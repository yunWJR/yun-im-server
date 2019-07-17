package com.yun.yunimserver.module.user.controller;

import com.yun.base.module.Bean.BaseRstBeanT;
import com.yun.yunimserver.config.NoNeedAccessAuthentication;
import com.yun.yunimserver.module.user.dtovo.UserVo;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.entity.UserAcct;
import com.yun.yunimserver.module.user.entity.UserInfo;
import com.yun.yunimserver.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "acct", value = "帐号、密码", required = true, dataType = "UserAcct")
    })
    @ApiOperation(value = "注册用户", notes = "")
    public BaseRstBeanT<User> register(@RequestBody UserAcct acct) {
        User user = userSv.register(acct);

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "pws", value = "密码", required = true, dataType = "string")
    })
    @ApiOperation(value = "登录", notes = "")
    public BaseRstBeanT<UserVo> login(@RequestParam @NotNull String name, @RequestParam @NotNull String pws) {

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
    @ApiImplicitParam(name = "info", value = "用户数据数据", required = true, dataType = "UserInfo")
    @ApiOperation(value = "更新用户信息", notes = "")
    public BaseRstBeanT<User> updateUserInfo(@RequestBody UserInfo info) {
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