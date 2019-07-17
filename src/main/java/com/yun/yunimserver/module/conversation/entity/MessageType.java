package com.yun.yunimserver.module.conversation.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.yun.yunimserver.module.ClientPlatformType;

/**
 * @author: yun
 * @createdOn: 2018/7/26 10:42.
 */

public enum MessageType {
    /**
     * Min
     */
    Min(0, "Min"),
    /**
     * 文本
     */
    Text(1, "文本"),
    /**
     * 语音
     */
    Audio(2, "语音"),
    /**
     * 图片
     */
    Piction(3, "图片"),
    /**
     * 文件
     */
    File(4, "文件"),

    Max(5, "Max");

    public static final String des = "消息内容类型:0-文本、1-语音、2-图片、3-文件";

    private final int type;
    private final String name;

    private MessageType(int type, String name) {
        this.type = type;

        this.name = name;
    }

    public static boolean isValidType(ClientPlatformType type) {
        if (type.getType() < Max.getType() && type.getType() > Min.type) {
            return true;
        }

        return false;
    }

    @JsonValue
    public int getType() {
        return type;
    }
}
