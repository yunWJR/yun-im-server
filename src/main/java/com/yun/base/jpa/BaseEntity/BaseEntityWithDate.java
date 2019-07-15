package com.yun.base.jpa.BaseEntity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Created by yun on 2018/5/25 15:42.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityWithDate {
    @Column(nullable = false)
    @CreatedDate
    private Long createTime;

    @Column(nullable = false)
    @LastModifiedDate
    private Long updateTime;

    /**
     * Gets register time.
     * @return the register time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * Sets register time.
     * @param createTime the register time
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets updateUserInfo time.
     * @return the updateUserInfo time
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets updateUserInfo time.
     * @param updateTime the updateUserInfo time
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}