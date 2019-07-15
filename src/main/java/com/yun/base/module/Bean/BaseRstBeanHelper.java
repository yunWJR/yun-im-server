package com.yun.base.module.Bean;

/**
 * The itemType Base rst bean helper.
 * @author: yun
 * @createdOn: 2018 /5/30 13:44.
 */
public class BaseRstBeanHelper {

    // region --Field

    // 1.模块标示
    private int module = BaseRstModuleType.Unknown.getModule();

    // 2.模块数据
    private String moduleName;

    // 3.模块数据
    private Object moduleData;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Base rst bean helper.
     */
    public BaseRstBeanHelper() {
    }

    /**
     * Instantiates a new Base rst bean helper.
     * @param module the module
     */
    public BaseRstBeanHelper(BaseRstModuleType module) {
        this.module = module.getModule();
        this.moduleName = module.getRemark();
    }

    /**
     * Instantiates a new Base rst bean helper.
     * @param module     the module
     * @param moduleName the module name
     */
    public BaseRstBeanHelper(BaseRstModuleType module, String moduleName) {
        this.module = module.getModule();
        this.moduleName = moduleName;
    }

    /**
     * Instantiates a new Base rst bean helper.
     * @param module     the module
     * @param moduleName the module name
     */
    public BaseRstBeanHelper(int module, String moduleName) {
        this.module = module;
        this.moduleName = moduleName;
    }

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    /**
     * Gets module.
     * @return the module
     */
    public int getModule() {
        return module;
    }

    /**
     * Sets module.
     * @param module the module
     */
    public void setModule(int module) {
        this.module = module;
    }

    /**
     * Gets module name.
     * @return the module name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets module name.
     * @param moduleName the module name
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Gets module data.
     * @return the module data
     */
    public Object getModuleData() {
        return moduleData;
    }

    /**
     * Sets module data.
     * @param moduleData the module data
     */
    public void setModuleData(Object moduleData) {
        this.moduleData = moduleData;
    }

    // endregion

    // region --Public method

    /**
     * Sur bean base rst bean.
     * @param data the data
     * @return the base rst bean
     */
    public BaseRstBean SurBean(Object data) {
        BaseRstBean rst = BaseRstBean.SurBean(data);

        rst = setModuleData(rst);

        return rst;
    }

    public BaseRstBean SurBeanT(Object data) {
        BaseRstBean rst = BaseRstBean.SurBean(data);

        rst = setModuleData(rst);

        return rst;
    }

    /**
     * Com err bean base rst bean.
     * @param errorMsg the error msg
     * @return the base rst bean
     */
    public BaseRstBean ComErrBean(String errorMsg) {
        BaseRstBean rst = BaseRstBean.ComErrBean(errorMsg);

        rst = setModuleData(rst);

        return rst;
    }

    // endregion

    // region --private method

    private BaseRstBean setModuleData(BaseRstBean rst) {
        rst.setModule(module);
        rst.setModuleName(moduleName);
        rst.setModuleData(moduleData);

        return rst;
    }

    // endregion

    // region --Other

    // endregion
}
