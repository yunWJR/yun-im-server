package com.yun.base.jpa.BaseEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author: yun
 * @createdOn: 2018/6/7 14:06.
 */

@MappedSuperclass
public class BaseEntityWithUuidDate extends BaseEntityWithDate {

    @Id
    @GenericGenerator(name = "UuidGenerator", strategy = "org.hibernate.pkId.UUIDGenerator")
    @GeneratedValue(generator = "UuidGenerator")
    private String id;

    /**
     * Gets pkId.
     * @return the pkId
     */
    public String getId() {
        return id;
    }

    /**
     * Sets pkId.
     * @param id the pkId
     */
    public void setId(String id) {
        this.id = id;
    }
}
