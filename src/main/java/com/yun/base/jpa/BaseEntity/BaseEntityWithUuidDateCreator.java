package com.yun.base.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by yun on 2018/5/25 16:37.
 */
@MappedSuperclass
public abstract class BaseEntityWithUuidDateCreator extends BaseEntityWithUuidDate {
    @Column(nullable = true)
    private String creatorId;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}