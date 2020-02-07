package com.zlb.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessToken {
    /**
     * token
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * token 的类型 bearer
     */
    @JsonProperty("token_type")
    private String tokenType;

    /**
     * token的过期时间
     */
    @JsonProperty("expires_in")
    private Long expiresIn;

    /**
     * token的范围
     */
    @JsonProperty("scope")
    private String scope;

    /**
     * 就是token的唯一的标识
     */
    @JsonProperty("jti")
    private String jti;
}
