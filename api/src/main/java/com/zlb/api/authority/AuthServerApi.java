package com.zlb.api.authority;

import com.zlb.api.model.AccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authority-server")
public interface AuthServerApi {

        @PostMapping("/oauth/token")
        AccessToken getToken(@RequestHeader("Authorization")String  authorization, @RequestParam("grant_type")String grantType, @RequestParam("username")String username, @RequestParam("password")String password);


}
