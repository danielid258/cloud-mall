package com.daniel.order.exception;


import com.daniel.order.enums.ResultEnum;

/**
 * Daniel on 2018/7/10.
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
