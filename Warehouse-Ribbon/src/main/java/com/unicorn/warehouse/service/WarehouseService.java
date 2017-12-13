package com.unicorn.warehouse.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.warehouse.model.Product;
import com.unicorn.warehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

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
        HttpEntity<String> formEntity = new HttpEntity<>("{id:" + id + ",num:" + num + "}");
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/put",
                formEntity, String.class);
    }

    @HystrixCommand(fallbackMethod = "Error")
    public Product view(int id) {
        HttpEntity<String> formEntity = new HttpEntity<>("{id:" + id + "}");
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/view",
                formEntity, Product.class);
    }

    @HystrixCommand(fallbackMethod = "Error")
    public String fetch(int id, int num) {
        HttpEntity<String> formEntity = new HttpEntity<>("{id:" + id + ",num:" + num + "}");
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/fetch",
                formEntity, String.class);
    }

    public String Error(String name) {
        return "sorry, error!";
    }
}
