package com.yun.base.jpa.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * The itemType Base entity with auto pkId.
 * @Description: user auto pkId
 * @Author: yun
 * @CreatedOn: 2018 /5/28 09:51.
 */
@MappedSuperclass
public abstract class BaseEntityWithAutoId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
}