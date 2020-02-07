package com.zlb.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录")
public class LoginController {

    @RequestMapping("/index")
    @ApiOperation(value = "登录")
    public String index(){
        System.out.println("=========路由到登入页面==========");
        return "index.html";
    }
}
