package com.daniel.product.service;

import com.daniel.product.model.ProductCategory;

import java.util.List;

/**
 * Daniel on 2018/7/2.
 */
public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
