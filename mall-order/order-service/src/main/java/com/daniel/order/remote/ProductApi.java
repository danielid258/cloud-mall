package com.daniel.order.remote;

import com.daniel.product.common.model.CommonProduct;
import com.daniel.product.common.model.ProductStockGeneric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Daniel on 2018/7/11.
 */
@FeignClient(name = "product-service")  //the server will be called that is registered in eureka and named product-service
public interface ProductApi {

    //call the interface in product-service
    @GetMapping("/remote/serve")
    String productRemoteServe();

    @PostMapping("/product/listForOrder")
    List<CommonProduct> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<ProductStockGeneric> decreaseStockInputList);
}
