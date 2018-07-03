package com.daniel.product.exception;

import com.daniel.product.enums.ResultEnum;

/**
 * Daniel on 2018/7/2.
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
