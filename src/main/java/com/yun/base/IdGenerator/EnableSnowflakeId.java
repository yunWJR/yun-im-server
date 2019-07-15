package com.yun.base.IdGenerator;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2018/7/25 14:23.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SnowflakeIdWorkerAutoConfigure.class, SnowflakeIdUtil.class})
public @interface EnableSnowflakeId {
}
