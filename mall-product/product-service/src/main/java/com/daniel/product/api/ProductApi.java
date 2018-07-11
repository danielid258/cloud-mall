package com.daniel.product.api;

import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.CommonProductStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Daniel on 2018/7/10.
 */
@FeignClient(name = "product")
public interface ProductApi {
    @PostMapping("/product/listForOrder")
    List<CommonProduct> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CommonProductStock> decreaseStockInputList);
}
