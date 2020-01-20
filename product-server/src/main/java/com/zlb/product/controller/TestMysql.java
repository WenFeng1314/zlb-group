package com.zlb.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="测试")
public class TestMysql {
    @ApiOperation(value = "测试接口")
    @GetMapping("/test")
    public void test(){
        System.out.println("测试成功！！！");
    }

}
