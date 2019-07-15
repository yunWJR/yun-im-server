package com.yun.yunimserver.module;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 客服端平台类型
 * @author: yun
 * @createdOn: 2018/7/25 16:51.
 */
public enum ClientPlatformType {
    /**
     *
     */
    Unknown(0),

    Min(1),

    /**
     * Ios
     */
    Ios(1),

    /**
     * Android
     */
    Android(2),

    /**
     * Pc
     */
    Pc(3),

    /**
     * Web
     */
    Web(4),

    Max(5);

    public static final String des = "平台:1-iOS、2-Pc、3-图片、4-Web";

    private final int type;

    private ClientPlatformType(int type) {
        this.type = type;
    }

    public static boolean isValidType(ClientPlatformType type) {
        if (type == null) {
            return false;
        }

        if (type.getType() < ClientPlatformType.Max.getType() && type.getType() >= ClientPlatformType.Min.getType()) {
            return true;
        }

        return false;
    }

    public static ClientPlatformType valueOfStr(String value) {
        int intVl = 0;

        try {
            intVl = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        return valueOfInt(intVl);
    }

    public static ClientPlatformType valueOfInt(int value) {
        for (int i = 0; i < ClientPlatformType.values().length; i++) {
            if (ClientPlatformType.values()[i].getType() == value) {
                return ClientPlatformType.values()[i];
            }
        }

        return null;
    }

    /**
     * json 序列号时，用 itemType
     * @return
     */
    @JsonValue
    public int getType() {
        return type;
    }
}
