package com.yun.base.module.Bean;

/**
 * @author: yun
 * @createdOn: 2018/6/7 14:19.
 */

public class RstBeanException extends RuntimeException {

    // region --Field

    private BaseRstBean rst;

    // endregion

    // region --Constructor

    public RstBeanException(BaseRstBean rst) {
        super();
        this.rst = rst;
    }

    public RstBeanException(Integer code, String error) {
        super();
        this.rst = new BaseRstBean(code, error);
    }

    // endregion

    // region --static method

    public static RstBeanException RstComErrBeanWithStr(String error) {
        return new RstBeanException(BaseRstBean.ComErrBean(error));
    }

    public static RstBeanException RstTypeErrBeanWithType(BaseRstCodeType type) {
        return new RstBeanException(new BaseRstBean(type));
    }

    public static RstBeanException RstCodeErrBean(Integer code) {
        return new RstBeanException(new BaseRstBean(code, ""));
    }

    // endregion   

    // region --Getter and Setter

    public BaseRstBean getRst() {
        return rst;
    }

    // endregion

    // region --Public method

    public void setRst(BaseRstBean rst) {
        this.rst = rst;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
