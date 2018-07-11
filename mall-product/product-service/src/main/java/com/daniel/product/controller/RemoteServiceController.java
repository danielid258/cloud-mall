package com.daniel.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Daniel on 2018/7/11.
 */
@RestController
public class RemoteServiceController {
    @GetMapping("/remote/serve")
    public String remoteServe() {
        return "remote served ... ";
    }
}
