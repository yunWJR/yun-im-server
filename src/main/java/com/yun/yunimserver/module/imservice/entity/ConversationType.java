package com.yun.yunimserver.module.imservice.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.yun.yunimserver.module.ClientPlatformType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The enum Dialog itemType.
 * @author: yun
 * @createdOn: 2018 /7/26 10:35.
 */
@ApiModel("会话类型")
public enum ConversationType {
    /**
     *
     */
    Unknown(0),

    /**
     * 单人聊天
     */
    @ApiModelProperty("单人聊天")
    Couple(1),

    /**
     * 群组聊天
     */
    @ApiModelProperty("多人聊天")
    Group(2),

    @ApiModelProperty("通知消息")
    Notification(3),

    @ApiModelProperty("应用")
    Application(4),

    /**
     * Dialog itemType max group itemType.
     */
    DialogTypeMax(5);

    public static final String des = "会话类型:1-单人聊天、2-群聊、3-通知消息、4-应用";

    private final int type;

    private ConversationType(int type) {
        this.type = type;
    }

    /**
     * Is valid itemType boolean.
     * @param type the itemType
     * @return the boolean
     */
    public static boolean isValidType(ClientPlatformType type) {
        if (type.getType() < DialogTypeMax.getType() && type.getType() >= Couple.getType()) {
            return true;
        }

        return false;
    }

    public static ConversationType getType(Integer code) {
        ConversationType item = null;
        for (int i = Couple.getType(); i < ConversationType.values().length; i++) {
            item = ConversationType.values()[i];
            if (code == item.getType()) {
                break;
            }
        }
        return item;
    }

    public boolean isEquelTo(Integer type) {
        if (type == null) {
            return false;
        }

        return (type == getType());
    }

    public boolean isChat() {
        return (this == Couple || this == Group);
    }

    /**
     * Gets itemType.
     * @return the itemType
     */
    @JsonValue
    public int getType() {
        return type;
    }
}
