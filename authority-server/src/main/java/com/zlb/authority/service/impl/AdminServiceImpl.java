package com.zlb.authority.service.impl;



import com.zlb.api.authority.AuthServerApi;
import com.zlb.api.model.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl {
    @Autowired
    private AuthServerApi authServerApi;

    @Value("${zlb.authorization}")
    private String authorization;

    @Value("${zlb.grant-type}")
    private String grantType ;
    public String getToken(String username,String password){

        AccessToken token = authServerApi.getToken(authorization, grantType, username, password);

        if (Objects.nonNull(token)){
            //存入redis
            return token.getAccessToken();
        }
       throw new RuntimeException("authority-server 调用获取token失败!");
    }
}
