package com.zlb.authority.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        System.out.println("=========路由到登入页面==========");
        return "index.html";
    }
}
