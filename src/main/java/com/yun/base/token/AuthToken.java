package com.yun.base.token;

import java.lang.annotation.*;

/**
 * AuthToken注解，用于注解需要验证 authToken的方法
 * @author: yun
 * @createdOn: 2018 /6/4 14:01.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthToken {
    /**
     * Para name string.
     * @return the string
     */
    String paraName() default "";
}