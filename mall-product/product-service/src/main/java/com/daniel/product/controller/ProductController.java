package com.daniel.product.controller;

import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.ProductStockGeneric;
import com.daniel.product.model.Product;
import com.daniel.product.model.ProductCategory;
import com.daniel.product.service.ProductCategoryService;
import com.daniel.product.service.ProductService;
import com.daniel.product.util.ResultVOUtil;
import com.daniel.product.vo.ProductCategoryVO;
import com.daniel.product.vo.ProductVO;
import com.daniel.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Daniel on 2018/7/3.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取上架商品 及所属产品分类列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
        //1. 查询上架商品
        List<Product> productList = productService.findUpAll();

        //2. 获取上架商品所属产品分类
        List<Integer> categoryTypeList = productList.stream().map(Product::getCategoryType).collect(Collectors.toList());

        //3. 查询产品分类
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCategoryName(productCategory.getCategoryName());
            productCategoryVO.setCategoryType(productCategory.getCategoryType());

            List<ProductVO> productVOList = new ArrayList<>();
            productList.stream().filter(product -> product.getCategoryType().equals(productCategory.getCategoryType())).forEach(product -> {
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(product, productVO);
                productVOList.add(productVO);
            });
            productCategoryVO.setProductVOList(productVOList);
            productCategoryVOList.add(productCategoryVO);
        }
        return ResultVOUtil.success(productCategoryVOList);
    }

    /**
     * 获取商品列表(给订单服务用)
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<CommonProduct> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }

    @PostMapping("/stock/decrease")
    public void decreaseStock(@RequestBody List<ProductStockGeneric> productStocks) {
        productService.decreaseStock(productStocks);
    }
}
