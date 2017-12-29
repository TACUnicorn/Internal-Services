package com.unicorn.warehouse.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.warehouse.model.Product;
import com.unicorn.warehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@Service
public class WarehouseService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "Error")
    public String put(int id, int num) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject jsonParams = JSON.parseObject("{\"id\":" + id + ", \"num\":" + num + "}");
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonParams.toJSONString(), headers);
        System.out.println(requestEntity.getBody().toString());
        String s = null;
        try {
           s = restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/put",
                    requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @HystrixCommand(fallbackMethod = "viewError")
    public Product view(int id) {
        return restTemplate.getForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/view?id=" + id, Product.class);
    }

    @HystrixCommand(fallbackMethod = "Error")
    public String fetch(int id, int num) {
        HttpEntity<String> formEntity = new HttpEntity<>("{\"id\":" + id + ", \"num\":" + num + "}");
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/fetch",
                formEntity, String.class);
    }

    public Product viewError(int id) {
        return new Product();
    }

    public String Error(int id, int num) {
        return "sorry, error!";
    }

}
