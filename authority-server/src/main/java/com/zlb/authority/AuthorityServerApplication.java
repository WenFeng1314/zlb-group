package com.zlb.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthorityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityServerApplication.class, args);
    }

}
