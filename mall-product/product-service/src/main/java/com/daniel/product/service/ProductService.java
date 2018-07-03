package com.daniel.product.service;

import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.CommonProductStock;
import com.daniel.product.model.Product;

import java.util.List;

/**
 * Daniel on 2018/7/2.
 */
public interface ProductService {
    /**
     * 查询上架商品列表
     */
    List<Product> findUpAll();

    /**
     * 查询ID在集合中的商品列表
     *
     * @param productIdList
     * @return
     */
    List<CommonProduct> findList(List<String> productIdList);

    /**
     * 减少库存
     *
     * @param decreaseStockInputList
     */
    void decreaseStock(List<CommonProductStock> decreaseStockInputList);
}
