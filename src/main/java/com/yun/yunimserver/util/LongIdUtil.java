package com.yun.yunimserver.util;

import com.yun.base.module.Bean.RstBeanException;

/**
 * @author: yun
 * @createdOn: 2019-07-02 16:13.
 */

public class LongIdUtil {

    public static Long getValidId(String idStr) {
        if (idStr == null || idStr.length() == 0) {
            throw RstBeanException.RstComErrBeanWithStr("id 无效");
        }

        try {
            return Long.valueOf(idStr);
        } catch (NumberFormatException e) {
            throw RstBeanException.RstComErrBeanWithStr("id 无效");
        }
    }
}
