package com.daniel.product.common.model;

import lombok.Data;

/**
 * Daniel on 2018/7/2.
 */
@Data
public class CommonProductStock {
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CommonProductStock() {
    }

    public CommonProductStock(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
