package com.daniel.product.repository;

import com.daniel.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Daniel on 2018/7/2.
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductStatus(Integer productStatus);

    List<Product> findByProductIdIn(List<String> productIdList);
}
