package com.daniel.product.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Daniel on 2018/7/2.
 */
@Data
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 产品分类名称·.
     */
    private String categoryName;

    /**
     * 产品分类编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
