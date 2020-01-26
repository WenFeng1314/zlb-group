package com.zlb.product.dao;

import com.zlb.product.domain.ProductCashSale;
import com.zlb.product.domain.ProductCashSaleExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface ProductCashSaleMapper {
    long countByExample(ProductCashSaleExample example);

    int deleteByExample(ProductCashSaleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductCashSale record);

    int insertSelective(ProductCashSale record);

    List<ProductCashSale> selectByExampleWithBLOBs(ProductCashSaleExample example);

    List<ProductCashSale> selectByExample(ProductCashSaleExample example);

    ProductCashSale selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductCashSale record, @Param("example") ProductCashSaleExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductCashSale record, @Param("example") ProductCashSaleExample example);

    int updateByExample(@Param("record") ProductCashSale record, @Param("example") ProductCashSaleExample example);

    int updateByPrimaryKeySelective(ProductCashSale record);

    int updateByPrimaryKeyWithBLOBs(ProductCashSale record);

    int updateByPrimaryKey(ProductCashSale record);
}