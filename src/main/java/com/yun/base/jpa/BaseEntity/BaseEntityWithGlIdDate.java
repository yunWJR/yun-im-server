package com.yun.base.jpa.BaseEntity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yun.base.IdGenerator.LongJson.LongJsonDeserializer;
import com.yun.base.IdGenerator.LongJson.LongJsonSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author: yun
 * @createdOn: 2018/7/25 13:07.
 */

@MappedSuperclass // 实体是一个超类，保证不会被 Spring 实例化
public class BaseEntityWithGlIdDate extends BaseEntityWithDate {

    @Id
    @GenericGenerator(name = "GenerateSnowflakeId", strategy = "com.yun.base.IdGenerator.GenerateSnowflakeId")
    @GeneratedValue(generator = "GenerateSnowflakeId")

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
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
