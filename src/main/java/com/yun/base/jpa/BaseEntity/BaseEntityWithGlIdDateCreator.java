package com.yun.base.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author: yun
 * @createdOn: 2018/7/25 15:53.
 */

@MappedSuperclass
public class BaseEntityWithGlIdDateCreator extends BaseEntityWithGlIdDate {
    @Column(nullable = true)
    private Long creatorId;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
