package com.yun.base.token;

import com.yun.base.module.Bean.BaseRstBean;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthToken验证的配置参数
 * @author: yun
 * @createdOn: 2018 /6/4 19:59.
 */
public class AuthTokenParam {

    // region --Field

    // 加密 key
    private String secretKey = "alksdjflalsdjflkasdjflkasjd";

    // auth token 的位置，1：header 默认值 2:para
    private Integer authTokenLocal = 1;

    // auth token的 key
    private String authTokenKeyName = "auth-token";

    private String tokenIssuer = "auth";

    // 过期时间
    private long ttlMillis = 1000 * 1000;

    // 忽略 token 为 null，测试时候用
    private boolean ignoreToken = false;

    // 采用 ThreadLocal 传输
    private boolean isThreadLocalMode = true;

    // 默认所有方法需要 token 验证，如果不需要验证，则加 NoAuthToken 注解
    private boolean isAllNeedTokenOn = true;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Auth token param.
     */
    public AuthTokenParam() {
    }

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    /**
     * Gets secret key.
     * @return the secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Sets secret key.
     * @param secretKey the secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Gets auth token local.
     * 1：header 默认值 2:para
     * @return the auth token local
     */
    public Integer getAuthTokenLocal() {
        return authTokenLocal;
    }

    /**
     * Sets auth token local.
     * 1：header 默认值 2:para
     * @param authTokenLocal the auth token local
     */
    public void setAuthTokenLocal(Integer authTokenLocal) {
        this.authTokenLocal = authTokenLocal;
    }

    /**
     * Gets auth token key name.
     * @return the auth token key name
     */
    public String getAuthTokenKeyName() {
        return authTokenKeyName;
    }

    /**
     * Sets auth token key name.
     * @param authTokenKeyName the auth token key name
     */
    public void setAuthTokenKeyName(String authTokenKeyName) {
        this.authTokenKeyName = authTokenKeyName;
    }

    /**
     * Gets token issuer.
     * @return the token issuer
     */
    public String getTokenIssuer() {
        return tokenIssuer;
    }

    /**
     * Sets token issuer.
     * @param tokenIssuer the token issuer
     */
    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    /**
     * Gets ttl millis.
     * @return the ttl millis
     */
    public long getTtlMillis() {
        return ttlMillis;
    }

    /**
     * Sets ttl millis.
     * @param ttlMillis the ttl millis
     */
    public void setTtlMillis(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    /**
     * Is ignore token boolean.
     * @return the boolean
     */
    public boolean isIgnoreToken() {
        return ignoreToken;
    }

    /**
     * Sets ignore token.
     * @param ignoreToken the ignore token
     */
    public void setIgnoreToken(boolean ignoreToken) {
        this.ignoreToken = ignoreToken;
    }

    public boolean isThreadLocalMode() {
        return isThreadLocalMode;
    }

    public void setThreadLocalMode(boolean threadLocalMode) {
        isThreadLocalMode = threadLocalMode;
    }

    public boolean isAllNeedTokenOn() {
        return isAllNeedTokenOn;
    }

    public void setAllNeedTokenOn(boolean allNeedTokenOn) {
        isAllNeedTokenOn = allNeedTokenOn;
    }

    // endregion

    // region --Public method

    public boolean shouldCheckToken() {
        return !this.isIgnoreToken();
    }

    public String getToken(HttpServletRequest rqt) {
        // 根据token获取登录信息
        String tkVal = null;
        if (getAuthTokenLocal() == 1) {
            tkVal = rqt.getHeader(getAuthTokenKeyName());
        } else if (getAuthTokenLocal() == 2) {
            tkVal = rqt.getParameter(getAuthTokenKeyName());
        } else {
            throw new AuthTokenException(BaseRstBean.ComErrBean("未指定token位置"));
        }

        return tkVal;
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
