package com.yun.base.token;

import com.yun.base.module.Bean.BaseRstBean;

/**
 * The itemType Auth token exception.
 * @author: yun
 * @createdOn: 2018 /6/6 13:21.
 */
public class AuthTokenException extends RuntimeException {

    // region --Field

    private BaseRstBean rst;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Auth token exception.
     * @param message the message
     */
    public AuthTokenException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Auth token exception.
     * @param rst the rst
     */
    public AuthTokenException(BaseRstBean rst) {
        super();
        this.rst = rst;
    }

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    /**
     * Gets rst.
     * @return the rst
     */
    public BaseRstBean getRst() {
        return rst;
    }

    /**
     * Sets rst.
     * @param rst the rst
     */
    public void setRst(BaseRstBean rst) {
        this.rst = rst;
    }

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
