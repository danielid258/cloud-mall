package com.daniel.order.enums;

import lombok.Getter;

/**
 * Daniel on 2018/7/10.
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    PRODUCT_STOCK_ERROR(3, "");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
