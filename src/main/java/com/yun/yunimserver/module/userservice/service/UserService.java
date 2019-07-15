package com.yun.yunimserver.module.userservice.service;

import com.yun.yunimserver.module.userservice.dtovo.UserVo;
import com.yun.yunimserver.module.userservice.entity.User;
import com.yun.yunimserver.module.userservice.entity.UserAcct;
import com.yun.yunimserver.module.userservice.entity.UserInfo;

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
     * Gets all userservice.
     * @return the all userservice
     */
    List<User> getAllUser();

    /**
     * Update base rst bean.
     * @param user the userservice
     * @return the base rst bean
     */
    User updateUserInfo(UserInfo user);

    /**
     * Add base rst bean.
     * @param acct the acct
     * @return the base rst bean
     */
    User register(UserAcct acct);

    /**
     * Gets userservice.
     * @param userId the userservice pkId
     * @return the userservice
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
