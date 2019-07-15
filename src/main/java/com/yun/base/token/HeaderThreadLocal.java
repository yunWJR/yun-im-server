package com.yun.base.token;

import io.netty.util.internal.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2018-12-06 15:49.
 */

public class HeaderThreadLocal {

    public static String platformName = "deviceType";

    private static ThreadLocal<Map> threadLocal = new ThreadLocal<>();

    private static Map<String, String> map = new HashMap<String, String>();

    public static void put(String key, String value) {
        map.put(key, value);

        threadLocal.set(map);
    }

    public static Map get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static int getPlatform() {
        String p = map.get(platformName);
        if (StringUtil.isNullOrEmpty(p)) {
            return -1;
        } else {
            return Integer.parseInt(p);
        }
    }
}
