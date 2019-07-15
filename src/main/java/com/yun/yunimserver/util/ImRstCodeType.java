package com.yun.yunimserver.util;

/**
 * @author: yun
 * @createdOn: 2018/7/30 14:32.
 */

public enum ImRstCodeType {

    // region --Field

    /**
     * 默认最大Code值，自定义 Code 需要大于该值
     */
    MaxDefCode(9999, "默认最大Code值，自定义 Code 需要大于该值"),

    MsgHasClientId(10000, "已经存储ClientId的消息"),
    CreateDialogFail_HasDialog(10001, "已经存在ClientId的消息"),

    MaxImCode(99999, "默认最大Code值，自定义 Code 需要大于该值");

    private Integer code;

    private String msg;

    // endregion

    // region --Constructor

    /**
     * enum 构造方法 -private
     * @param code code
     * @param msg  msg
     */
    private ImRstCodeType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // endregion

    // region --Getter and Setter

    /**
     * Gets by code.
     * @param code the code
     * @return the by code
     */
    public static ImRstCodeType getByCode(Integer code) {
        ImRstCodeType showStatus = null;
        for (int i = 0; i < ImRstCodeType.values().length; i++) {
            showStatus = ImRstCodeType.values()[i];
            if (code.equals(showStatus.getCode())) {
                break;
            }
        }

        return showStatus;
    }

    /**
     * Gets code.
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     * @param code the code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets msg.
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    // endregion

    // region --Public method

    /**
     * Sets msg.
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
