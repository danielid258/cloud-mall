package com.daniel.product.controller;

import com.daniel.product.ProductServiceApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Daniel on 2018/7/3.
 */
@Slf4j
public class ProductControllerTest extends ProductServiceApplicationTests{
    @Test
    public void list() throws Exception {
        String response = mockMvc.perform(get("/product/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        log.info(response);
    }

    @Test
    public void listForOrder() throws Exception {
        List<String> params = Arrays.asList("157875196366160022", "157875227953464068");
        String response = mockMvc.perform(post("/product/list/order")
                .content(params.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info(response);
    }

    @Test
    public void decreaseStock() throws Exception {

    }

}