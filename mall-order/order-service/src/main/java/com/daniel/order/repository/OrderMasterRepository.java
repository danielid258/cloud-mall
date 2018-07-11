package com.daniel.order.repository;

import com.daniel.order.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daniel on 2018/7/10.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
