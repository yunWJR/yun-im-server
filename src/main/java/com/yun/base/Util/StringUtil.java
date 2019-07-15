package com.yun.base.Util;

/**
 * @author: yun
 * @createdOn: 2018/6/8 09:08.
 */

public class StringUtil {

    // region --Field

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }

        if (str.length() == 0) {
            return true;
        }

        return false;
    }

    public static boolean hasCtn(String str) {
        return !isNullOrEmpty(str);
    }

    // endregion   

    // region --Getter and Setter

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
