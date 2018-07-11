package com.daniel.order.controller;

import com.daniel.order.remote.ProductApi;
import com.daniel.product.common.model.CommonProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Daniel on 2018/7/11.
 * <p>
 * communicate between services
 */
@Slf4j
@RestController
@RequestMapping("/remote/call")
public class RemoteCallController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductApi productApi;


    /**
     * By RestTemplate
     *
     * @return
     */
    @GetMapping("/restTemplate")
    public String remoteCallByRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8082/remote/serve";
        String object = restTemplate.getForObject(url, String.class);
        log.info("response data:{}", object);
        return object;
    }


    /**
     * get serviceInstance by LoadBalancerClient,then get host and  port;
     *
     *
     *
     * @return
     */
    @GetMapping("/loadBalancerClient")
    public String remoteCallByLoadBalancerClient() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT-SERVICE");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
        url += "/remote/serve";

        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.getForObject(url, String.class);
        log.info("response data:{}", object);
        return object;
    }

    /**
     * get by eureka Application name:PRODUCT-SERVICE
     *
     * @return
     */
    @GetMapping("/loadBalanced")
    public String remoteCallByLoadBalanced() {
        String object = this.restTemplate.getForObject("http://PRODUCT-SERVICE/remote/serve", String.class);
        log.info("response data:{}", object);
        return object;
    }

    /**
     * get by @FeignClient
     *
     * @return
     */
    @GetMapping("/feign")
    public String remoteCallByFeign() {
        String object = productApi.productRemoteServe();
        log.info("response data:{}", object);
        return object;
    }

    /**
     * get product list by @FeignClient
     *
     * @return
     */
    @GetMapping("/product/list")
    public String productList() {
        List<CommonProduct> list = productApi.listForOrder(Arrays.asList("", ""));
        log.info("response data:{}", list);
        return "OK";
    }
}
