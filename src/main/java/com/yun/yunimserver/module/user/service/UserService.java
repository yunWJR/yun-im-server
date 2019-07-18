package com.yun.yunimserver.module.user.service;

import com.yun.yunimserver.module.user.dtovo.UserVo;
import com.yun.yunimserver.module.user.entity.User;
import com.yun.yunimserver.module.user.entity.UserAcct;
import com.yun.yunimserver.module.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The interface User service.
 * @Description:
 * @Author: yun
 * @CreatedOn: 2018 /5/28 14:42.
 */
public interface UserService {
    /**
     * Gets all user.
     * @return the all user
     */
    List<UserVo> getAllUser();

    /**
     * Update base rst bean.
     * @param user the user
     * @return the base rst bean
     */
    User updateUserInfo(UserInfo user);

    /**
     * Add base rst bean.
     * @param acct the acct
     * @return the base rst bean
     */
    User register(UserAcct acct, String nickName, String phone);

    /**
     * Gets user.
     * @param userId the user pkId
     * @return the user
     */
    User getValidUser(Long userId);

    User getUserByName(String userName);

    /**
     * Login base rst bean.
     * @param name the name
     * @param pws  the pws
     * @return the base rst bean
     */
    UserVo login(String name, String pws);

    User getUserInfo();

    User checkTokenUser(String tokenStr, HttpServletRequest request);
}
