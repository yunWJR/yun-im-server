package com.yun.yunimserver.module.userservice.entity;

public enum UserStatus {
    NotOnline(0),
    Online(1),
    Hide(2);

    private final int status;

    private UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
