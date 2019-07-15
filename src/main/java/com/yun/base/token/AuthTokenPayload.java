package com.yun.base.token;

import java.util.Map;

/**
 * The itemType Auth token payload.
 * @author: yun
 * @createdOn: 2018 /6/5 14:21.
 */
public class AuthTokenPayload {

    // region --Field

    private String userId;

    private Map<String, Object> claims;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Auth token payload.
     */
    public AuthTokenPayload() {
    }

    /**
     * Instantiates a new Auth token payload.
     * @param userId the userservice pkId
     */
    public AuthTokenPayload(String userId) {
        this.userId = userId;
    }

    /**
     * Instantiates a new Auth token payload.
     * @param claims the claims
     */
    public AuthTokenPayload(Map<String, Object> claims) {
        this.claims = claims;
    }

    /**
     * Instantiates a new Auth token payload.
     * @param userId the userservice pkId
     * @param claims the claims
     */
    public AuthTokenPayload(String userId, Map<String, Object> claims) {
        this.userId = userId;
        this.claims = claims;
    }

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    /**
     * Gets userservice pkId.
     * @return the userservice pkId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets userservice pkId.
     * @param userId the userservice pkId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets claims.
     * @return the claims
     */
    public Map<String, Object> getClaims() {
        return claims;
    }

    /**
     * Sets claims.
     * @param claims the claims
     */
    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }

    // endregion

    // region --Public method

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
