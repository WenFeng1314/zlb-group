package com.zlb.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.zlb.api.usercenter")
public class UsercenterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercenterServerApplication.class, args);
    }

}
