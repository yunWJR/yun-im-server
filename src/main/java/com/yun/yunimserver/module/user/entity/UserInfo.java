package com.yun.yunimserver.module.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

/**
 * The itemType User info.
 * @Description:
 * @Author: yun
 * @CreatedOn: 2018 /5/28 20:07.
 */
@Embeddable
public class UserInfo {
    @Column
    private String headUrl;

    @Column
    private String nickName;

    @Column
    private String phone;

    @Column
    @Email
    private String email;

    @Column
    private String company;

    @Column
    private String remark;

    /**
     * Gets addRemark.
     * @return the addRemark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets addRemark.
     * @param remark the addRemark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone.
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
