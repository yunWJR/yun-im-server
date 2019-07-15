package com.yun.yunimserver.config.authtoken;

/**
 * The itemType Auth token config.
 * @author: yun
 * @createdOn: 2018 /6/4 19:45.
 */
// @Configuration

import com.yun.base.token.AuthTokenParam;
import com.yun.base.token.AuthTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @EnableAuthToken
@Configuration
public class AuthTokenConfig {

    // region --Field

    // endregion

    // region --Constructor

    // endregion

    // region --static method

    // endregion

    // region --Getter and Setter

    // endregion

    // region --Public method

    /**
     * Auth param auth token param.
     * @return the auth token param
     */
    @Bean
    public AuthTokenParam authParam() {
        AuthTokenParam param = new AuthTokenParam();
        param.setSecretKey("adssssas");
        param.setTtlMillis(999999999);
        param.setIgnoreToken(false);
        param.setAllNeedTokenOn(true);
        param.setThreadLocalMode(true);

        return param;
    }

    /**
     * Jwt helper auth token util.
     * @return the auth token util
     */
    @Bean
    public AuthTokenUtil jwtHelper() {
        AuthTokenUtil jwtHelper = new AuthTokenUtil(authParam());

        return jwtHelper;
    }

    // @Bean
    // public ObjectMapper objectMapper() {
    //     return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    // }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
