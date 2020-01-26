package com.zlb.product.controller;

import com.zlb.product.domain.ProductCashSale;
import com.zlb.product.service.ProductCashSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="测试")
public class TestMysql {
    @Autowired
    private ProductCashSaleService productCashSaleService;

    @ApiOperation(value = "测试接口")
    @GetMapping("/test")
    public void test(){
        List<ProductCashSale> list=productCashSaleService.selectProductCashSale();
        System.out.println("总数居条数:"+list.size());
        System.out.println("测试成功！！！");
    }

}
