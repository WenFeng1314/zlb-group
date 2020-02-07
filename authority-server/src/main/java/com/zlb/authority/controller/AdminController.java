package com.zlb.authority.controller;


import com.zlb.api.usercenter.UsercenterServerApi;
import com.zlb.authority.service.impl.AdminServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AdminController implements UsercenterServerApi {
    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public ResponseEntity<String> login(String username, String password) {
        String token = adminService.getToken(username, password);
        return ResponseEntity.ok(token);
    }


//    @Override
//    public AccessToken getToken(String authorization, String grantType, String username, String password) {
//
//        AccessToken token = authServerApi.getToken(authorization, grantType, username, password);
//        if (Objects.nonNull(token)){
//            //存入redis
//            return token;
//        }
//        throw new RuntimeException("authority-server 调用获取token失败!");
//    }


}
