package com.daniel.order.controller;

import com.daniel.order.properties.DemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Daniel on 2018/7/9.
 */
@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Autowired
    private DemoProperties demoProperties;

    @GetMapping("/demo")
    public DemoProperties demoProperties() {
        return demoProperties;
    }
}
