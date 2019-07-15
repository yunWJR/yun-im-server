package com.yun.yunimserver.module.userservice.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The itemType User acct.
 * @author: yun
 * @createdOn: 2018 /6/6 10:51.
 */
@Embeddable
public class UserAcct {

    // region --Field

    @Column(nullable = false, unique = true) // 不能重复
    @Length(min = 0, max = 100)
    private String name;

    @Column(nullable = false)
    @Length(min = 0, max = 100)
    private String pws;

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    /**
     * Gets pws.
     * @return the pws
     */
    public String getPws() {
        return pws;
    }

    /**
     * Sets pws.
     * @param pws the pws
     */
    public void setPws(String pws) {
        this.pws = pws;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
