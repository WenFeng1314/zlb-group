package com.zlb.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableFeignClients(basePackages = {"com.zlb.api.authority"})
public class AuthorityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityServerApplication.class, args);
    }

}
