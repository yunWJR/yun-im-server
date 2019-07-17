package com.yun.base.module.Bean;

/**
 * @author: yun
 * @createdOn: 2018/5/30 11:18.
 */

public enum BaseRstModuleType {

    // region --Field

    Unknown(0, "默认值"),

    Controller(1, "controller"),

    Service(2, "service"),

    Dao(3, "dtovo"),

    Entity(4, "model"),

    /**
     * 默认最大module值，自定义 module 需要大于该值
     */
    MaxDefCode(999, "默认最大module值");

    private Integer module;

    private String remark;

    // endregion

    // region --Constructor

    /**
     * enum 构造方法 -private
     * @param module module
     * @param remark addRemark
     */
    private BaseRstModuleType(Integer module, String remark) {
        this.module = module;
        this.remark = remark;
    }

    // endregion

    // region --Getter and Setter

    /**
     * Gets by module.
     * @param code the module
     * @return the by module
     */
    public static BaseRstModuleType getByCode(Integer code) {
        BaseRstModuleType showStatus = null;
        for (int i = 0; i < BaseRstModuleType.values().length; i++) {
            showStatus = BaseRstModuleType.values()[i];
            if (code.equals(showStatus.getModule())) {
                break;
            }
        }

        return showStatus;
    }

    /**
     * Gets module.
     * @return the module
     */
    public Integer getModule() {
        return module;
    }

    /**
     * Sets module.
     * @param module the module
     */
    public void setModule(Integer module) {
        this.module = module;
    }

    /**
     * Gets addRemark.
     * @return the addRemark
     */
    public String getRemark() {
        return remark;
    }

    // endregion

    // region --Public method

    /**
     * Sets addRemark.
     * @param remark the addRemark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
