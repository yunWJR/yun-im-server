package com.yun.base.jpa.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author: yun
 * @createdOn: 2018/7/20 13:21.
 */

@MappedSuperclass
public class BaseEntityWithAutoIdDate extends BaseEntityWithDate {

    // region --Field

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion   

    /**
     * Gets pkId.
     * @return the pkId
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets pkId.
     * @param id the pkId
     */
    public void setId(Long id) {
        this.id = id;
    }

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
