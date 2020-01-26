package com.zlb.product.service.impl;

import com.zlb.product.dao.ProductCashSaleMapper;
import com.zlb.product.domain.ProductCashSale;
import com.zlb.product.domain.ProductCashSaleExample;
import com.zlb.product.service.ProductCashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCashSaleServiceImpl implements ProductCashSaleService {
    @Autowired
    private ProductCashSaleMapper productCashSaleMapper;
    @Override
    public List<ProductCashSale> selectProductCashSale() {
        ProductCashSaleExample cashSaleExample = new ProductCashSaleExample();
        cashSaleExample.createCriteria()
                .andDelEqualTo(false);
        List<ProductCashSale> list = productCashSaleMapper.selectByExample(cashSaleExample);
        return list;
    }
}
