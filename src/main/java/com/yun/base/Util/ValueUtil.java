package com.yun.base.Util;

/**
 * @author: yun
 * @createdOn: 2018/7/26 15:25.
 */

public class ValueUtil {

    // region --Field

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    public static boolean isNullOrZeroLong(Long value) {
        if (value == null) {
            return true;
        }

        if (value == 0) {
            return true;
        }

        return false;
    }

    public static boolean isNullOrZeroInteger(Integer value) {
        if (value == null) {
            return true;
        }

        if (value == 0) {
            return true;
        }

        return false;
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
