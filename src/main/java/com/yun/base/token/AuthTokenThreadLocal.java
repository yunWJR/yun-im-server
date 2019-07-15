package com.yun.base.token;

/**
 * @author: yun
 * @createdOn: 2018-11-29 10:29.
 */

public class AuthTokenThreadLocal {

    private static ThreadLocal<AuthTokenPayload> threadLocal = new ThreadLocal<>();

    // region --Field

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    // endregion

    // region --Public method

    public static void put(AuthTokenPayload t) {
        threadLocal.set(t);
    }

    public static AuthTokenPayload get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
