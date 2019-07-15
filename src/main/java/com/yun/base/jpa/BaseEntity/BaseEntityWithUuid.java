package com.yun.base.jpa.BaseEntity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * The itemType Base entity with uuid.
 */
@MappedSuperclass // 实体是一个超类，保证不会被 Spring 实例化
public abstract class BaseEntityWithUuid {
    @Id
    @GenericGenerator(name = "UuidGenerator", strategy = "org.hibernate.pkId.UUIDGenerator")
    //这个是hibernate的注解->生成32位UUID
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