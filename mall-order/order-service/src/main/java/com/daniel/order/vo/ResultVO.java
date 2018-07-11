package com.daniel.order.vo;

import lombok.Data;

/**
 * Daniel on 2018/7/10.
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
