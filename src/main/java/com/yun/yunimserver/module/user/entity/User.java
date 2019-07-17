package com.yun.yunimserver.module.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.yun.base.Util.StringUtil;
import com.yun.base.jpa.BaseEntity.BaseEntityWithGlIdDateCreator;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * The itemType User.
 */
@Entity
@Data
public class User extends BaseEntityWithGlIdDateCreator {

    // region --Field

    @Embedded
    @JsonUnwrapped
    private UserAcct userAcct;

    @Column
    private String token;

    @Embedded
    @JsonUnwrapped
    private UserInfo userInfo;

    /**
     * 最后一次登录时间
     */
    @Column
    private Long lastLoadTime = 0L;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new User.
     */
    public User() {
        userInfo = new UserInfo();
    }

    /**
     * Instantiates a new User.
     * @param userAcct the user acct
     */
    public User(UserAcct userAcct) {
        this.userAcct = userAcct;
        this.userInfo = new UserInfo();
    }

    // endregion

    @JsonIgnore
    public boolean isInValidUser() {
        // return getInfo().getEnabled().equals(0);
        return false;
    }

    @JsonIgnore
    public String getDisplayName() {
        if (this.getUserInfo() == null) {
            return this.userAcct.getName();
        }

        if (StringUtil.isNullOrEmpty(this.getUserInfo().getNickName())) {
            return this.userAcct.getName();
        }

        return this.getUserInfo().getNickName();
    }
}
