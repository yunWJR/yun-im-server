package com.yun.yunimserver.module.friend.dtovo;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author: yun
 * @createdOn: 2019-07-17 15:14.
 */

public enum UserStatusType {
    /**
     *
     */
    Min(1, "min"),
    WaitCheck(1, "等待对方验证"),
    Reject(2, "对方拒绝"),
    AddSuc(3, "已添加"),
    Max(3, "Max");

    public static final String des = "发送者状态:1-等待对方验证、2-对方拒绝 3-已添加";

    private final Integer type;
    private final String name;

    private UserStatusType(Integer type, String name) {
        this.type = type;

        this.name = name;
    }

    public static boolean isValidItem(UserStatusType type) {
        if (type == null) {
            return false;
        }

        if (type.getType() <= Max.getType() && type.getType() >= Min.getType()) {
            return true;
        }

        return false;
    }

    public static boolean isValidType(Integer type) {
        if (type == null) {
            return false;
        }

        if (type <= Max.getType() && type >= Min.getType()) {
            return true;
        }

        return false;
    }

    public static UserStatusType getByCode(Integer code) {
        for (UserStatusType item : values()) {
            if (item.getType().equals(code)) {
                return item;
            }
        }

        return null;
    }

    @JsonValue
    public Integer getType() {
        return type;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public boolean isEqualToInt(Integer type) {
        if (type == null) {
            return false;
        }

        return getType().equals(type);
    }
}
