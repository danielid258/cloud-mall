package com.daniel.product.vo;

import lombok.Data;

/**
 * Daniel on 2018/7/3.
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
