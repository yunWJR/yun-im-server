package com.yun.yunimserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019-07-12 17:04.
 */

@Component
public class SpringEvnConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    private Boolean isProEvn;

    public boolean isProEvn() {
        if (isProEvn == null) {
            isProEvn = "pro".equals(profile);
        }

        return isProEvn;
    }

    public Integer isProValue() {
        return isProEvn ? 1 : 0;
    }
}
