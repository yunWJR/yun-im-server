package com.yun.yunimserver.module;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yun.base.module.Bean.RstBeanException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * @author: yun
 * @createdOn: 2019-07-02 13:29.
 */

public class BaseServiceImpl {
    public JPAQueryFactory queryFactory;
    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public void throwCommonError(String error) {
        throw RstBeanException.RstComErrBeanWithStr(error);
    }
}
