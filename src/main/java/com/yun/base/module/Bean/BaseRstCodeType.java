package com.yun.base.module.Bean;

/**
 * The enum Base rst code itemType.
 * @author: yun
 * @createdOn: 2018 /5/29 15:42.
 */
public enum BaseRstCodeType {

    // region --Field

    /**
     * None base rst code itemType.
     */
    None(0, "默认值"),

    /**
     * 成功值
     */
    Suc(200, ""),

    /**
     * No found
     */
    NoFound(404, ""),

    /**
     * Internal error
     */
    InternalError(500, "服务器异常"),

    // 自定义应该大于 505->web默认错误码最大值

    /**
     * 通用错误
     */
    ComErr(600, "错误"),

    /**
     * token 错误（未知错误）.
     */
    TokenUnknown(601, "token 错误（未知错误）"),

    /**
     * No token
     */
    NoToken(602, "无token"),

    /**
     * authtoken error
     */
    TokenError(603, "token错误"),

    /**
     * authtoken error
     */
    TokenExp(604, "token过期"),

    /**
     * 数据错误.
     */
    InValidData(701, "数据错误"),

    /**
     * 无操作权限.
     */
    NoLimit(801, "无操作权限"),

    /**
     * 默认最大Code值，自定义 Code 需要大于该值
     */
    MaxDefCode(9999, "默认最大Code值，自定义 Code 需要大于该值");

    private Integer code;

    private String msg;

    // endregion

    // region --Constructor

    /**
     * enum 构造方法 -private
     * @param code code
     * @param msg  msg
     */
    private BaseRstCodeType(Integer code, String msg) {
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
    public static BaseRstCodeType getByCode(Integer code) {
        BaseRstCodeType showStatus = null;
        for (int i = 0; i < BaseRstCodeType.values().length; i++) {
            showStatus = BaseRstCodeType.values()[i];
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