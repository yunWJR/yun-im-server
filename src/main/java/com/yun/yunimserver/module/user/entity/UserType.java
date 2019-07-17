package com.yun.yunimserver.module.user.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum User itemType.
 * @Description:
 * @Author: yun
 * @CreatedOn: 2018 /5/28 14:24.
 */
public enum UserType {
    /**
     * None user itemType.
     */
    None(0, "无权限"),

    /**
     * Admin user itemType.
     */
    Admin(1, "Admin"),

    /**
     * Pj mg user itemType.
     */
    PjMg(2, "项目管理者"),

    /**
     * Pj read user itemType.
     */
    PjRead(3, "项目观察者");

    private Integer code;  //对应括号里面第一个值,即 0 或 1
    private String description; //对应括号里面第二个值,即不显示 或  显示
    //如果括号里还有其它定义的值,可定义相应的变量与之对应即可

    //enum 构造方法,注意这里是 private 私有的修饰符啊
    private UserType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets by code.
     * @param code the code
     * @return the by code
     */
    //通过code,即 0 或 1 获取对应 enum 对象
    public static UserType getByCode(Integer code) {
        UserType showStatus = null;
        for (int i = 0; i < UserType.values().length; i++) {
            showStatus = UserType.values()[i];
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
    //各个变量的getter,setter方法,注意这里是 public 公共的修饰符啊
    @JsonValue
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
     * Gets description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
