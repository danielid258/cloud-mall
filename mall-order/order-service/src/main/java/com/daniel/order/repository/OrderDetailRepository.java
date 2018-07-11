package com.daniel.order.repository;

import com.daniel.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daniel on 2018/7/10.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
