package com.zlb.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "登录")
public class LoginController {

    @RequestMapping("/login")
    @ApiOperation(value = "登录")
    public String login(){
        System.out.println("=========路由到登入页面==========");
        return "index.html";
    }
}
