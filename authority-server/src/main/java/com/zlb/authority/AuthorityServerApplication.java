package com.zlb.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthorizationServer //开启授权服务器功能
@EnableResourceServer   //开启资源服务器功能
public class AuthorityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityServerApplication.class, args);
    }

}
