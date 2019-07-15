package com.yun.yunimserver.module.userservice.limit;

import com.yun.base.Util.JrpUtil;
import com.yun.base.module.Bean.BaseRstCodeType;
import com.yun.base.module.Bean.RstBeanException;
import com.yun.base.token.AuthTokenPayload;
import com.yun.yunimserver.module.userservice.entity.User;
import com.yun.yunimserver.module.userservice.entity.UserJpa;
import com.yun.yunimserver.module.userservice.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The itemType User util.
 * @author: yun
 * @createdOn: 2018 /6/6 14:38.
 */
@Component
public class UserUtil {

    // region --Field

    @Autowired
    private UserJpa userJpa;

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    // endregion

    // region --Public method

    /**
     * Gets userservice.
     * @param tokenPayload the token payload
     * @return the userservice
     */
    public User getUser(AuthTokenPayload tokenPayload) {
        Long userId = Long.valueOf(tokenPayload.getUserId());

        User us = JrpUtil.findById(userJpa, userId);
        if (us != null) {
            return us;
        }

        throw RstBeanException.RstComErrBeanWithStr("无用户");
    }

    /**
     * Has limit base rst bean.
     * @param tokenPayload the token payload
     * @param type         the itemType
     * @return the base rst bean
     */
    public void checkAccess(AuthTokenPayload tokenPayload, UserType type) {
        User us = JrpUtil.findById(userJpa, Long.valueOf(tokenPayload.getUserId()));
        if (us != null) {
            if (us.getUserType().equals(type.getCode())) {
                return;
            }
        }

        // todo 不应该这么检查
        throw RstBeanException.RstTypeErrBeanWithType(BaseRstCodeType.NoLimit);
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
