package com.daniel.product.common.model;

import lombok.Data;

/**
 * Daniel on 2018/7/2.
 */
@Data
public class ProductStockGeneric {
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public ProductStockGeneric() {
    }

    public ProductStockGeneric(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
