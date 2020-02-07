package com.zlb.usercenter.controller;

import com.zlb.api.usercenter.UsercenterServerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginGetToken {
    @Autowired
    private UsercenterServerApi usercenterServerApi;
    @PostMapping("/login")
    public ResponseEntity<String> login(String username,String password){
        ResponseEntity<String> login = usercenterServerApi.login(username, password);
        return login;
    }
}
