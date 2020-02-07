package com.zlb.api.usercenter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usercenter-server")
public interface UsercenterServerApi {

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestParam("username")String username, @RequestParam("password")String password);
}
