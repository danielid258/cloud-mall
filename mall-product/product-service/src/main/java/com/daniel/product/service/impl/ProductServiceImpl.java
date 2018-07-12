package com.daniel.product.service.impl;

import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.ProductStockGeneric;
import com.daniel.product.enums.ProductStatusEnum;
import com.daniel.product.enums.ResultEnum;
import com.daniel.product.exception.ProductException;
import com.daniel.product.model.Product;
import com.daniel.product.repository.ProductRepository;
import com.daniel.product.service.ProductService;
import com.daniel.product.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Daniel on 2018/7/2.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<Product> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<CommonProduct> findList(List<String> productIdList) {
        return productRepository.findByProductIdIn(productIdList).stream()
                .map(product -> {
                    CommonProduct commonProduct = new CommonProduct();
                    BeanUtils.copyProperties(product, commonProduct);
                    return commonProduct;
                }).collect(Collectors.toList());
    }

    @Override
    public void decreaseStock(List<ProductStockGeneric> productStocks) {
        List<Product> productList = decreaseStockProcess(productStocks);

        //减少库存事务执行成功 发送mq消息
        List<CommonProduct> commonProductList = productList.stream().map(e -> {
            CommonProduct commonProduct = new CommonProduct();
            BeanUtils.copyProperties(e, commonProduct);
            return commonProduct;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(commonProductList));

    }

    /**
     * 减少库存 事务控制
     *
     * @param productStocks
     * @return
     */
    @Transactional
    private List<Product> decreaseStockProcess(List<ProductStockGeneric> productStocks) {
        List<Product> productList = new ArrayList<>();
        for (ProductStockGeneric productStock : productStocks) {
            Optional<Product> productOptional = productRepository.findById(productStock.getProductId());
            //判断商品是否存在
            if (!productOptional.isPresent())
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);

            Product product = productOptional.get();
            //库存是否足够
            Integer result = product.getProductStock() - productStock.getProductQuantity();
            if (result < 0)
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);

            //update product stock
            product.setProductStock(result);
            productRepository.save(product);
            productList.add(product);
        }
        return productList;
    }
}
