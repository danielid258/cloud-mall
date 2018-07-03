package com.daniel.product.service.impl;

import com.daniel.product.ProductServiceApplicationTests;
import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.CommonProductStock;
import com.daniel.product.model.Product;
import com.daniel.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Daniel on 2018/7/3.
 */
public class ProductServiceImplTest extends ProductServiceApplicationTests{
    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<Product> productList = productService.findUpAll();
        Assert.assertTrue(productList.size() > 0);
    }

    @Test
    public void findList() throws Exception {
        List<CommonProduct> list = productService.findList(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception {
        CommonProductStock decreaseStockInput = new CommonProductStock("157875196366160022", 2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }

}