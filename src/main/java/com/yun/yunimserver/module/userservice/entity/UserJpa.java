package com.yun.yunimserver.module.userservice.entity;

import com.yun.base.jpa.Repository.BaseJpaRepositoryByAutoId;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpa extends BaseJpaRepositoryByAutoId<User> {
    boolean existsByUserAcctName(String name);

    boolean existsByIdIn(List<Long> ids);

    User findUserByUserAcctName(String name);

    User findUserById(Long userId);

    List<User> findAllById(Long userId);

    List<User> findAllByIdIn(List<Long> userIds);

    // 部分查询，需要 User 实现相应的构造函数
    @Query("SELECT new User(us.id,us.userAcct,us.userType,us.userInfo) FROM User us")
    List<User> findUsers();

    // map 格式不好
    @Query(value = "SELECT new map(us.id,us.userAcct,us.userType,us.userInfo) FROM User us")
    Object[] findUsersOfCt();
}
