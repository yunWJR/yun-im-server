package com.yun.yunimserver.util;

import com.yun.base.module.Bean.BaseRstCodeType;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.base.token.HeaderThreadLocal;
import com.yun.yunimserver.module.user.entity.User;

/**
 * @author: yun
 * @createdOn: 2019-07-02 15:36.
 */

public class RequestUtil {
    private static final String CONTROLLER_PARA = "CONTROLLER_PARA";

    public static void saveLoginUser(User user) {
        ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, user);
    }

    public static User getLoginUser() {
        User loginUser = (User) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (loginUser == null) {
            throw RstBeanException.RstTypeErrBeanWithType(BaseRstCodeType.NoToken);
        }
        return loginUser;
    }

    public static Long getLoginUserId() {
        return null;
    }

    public static String getControllerPara() {
        return null;
    }

    public static void saveDeviceType(Integer type) {
        ThreadLocalMap.put(GlobalConstant.Sys.DEVICE_TYPE_DTO, type);
    }

    public static Integer getDeviceType() {
        Integer type = (Integer) ThreadLocalMap.get(GlobalConstant.Sys.DEVICE_TYPE_DTO);

        if (type == null) {
            return HeaderThreadLocal.getPlatform();
        }

        return type;
    }
}
