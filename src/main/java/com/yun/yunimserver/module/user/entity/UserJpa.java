package com.yun.yunimserver.module.user.entity;

import com.yun.base.jpa.Repository.BaseJpaRepositoryByAutoId;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpa extends BaseJpaRepositoryByAutoId<User> {
    boolean existsByUserAcctName(String name);

    User findUserByUserAcctName(String name);
}
