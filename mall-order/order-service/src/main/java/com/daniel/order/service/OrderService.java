package com.daniel.order.service;


import com.daniel.order.dto.OrderDTO;

/**
 * Daniel on 2018/7/10.
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
