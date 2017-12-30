package com.unicorn.warehouse.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.warehouse.model.BasicResponse;
import com.unicorn.warehouse.model.Product;
import com.unicorn.warehouse.model.ProductTmp;
import com.unicorn.warehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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
    public BasicResponse<String> put(@RequestBody ProductTmp productTmp) {
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//
//        JSONObject jsonParams = JSON.parseObject("{\"id\":" + id + ", \"num\":" + num + "}");
//        HttpEntity<String> requestEntity = new HttpEntity<>(jsonParams.toJSONString(), headers);
//        System.out.println(requestEntity.getBody().toString());
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/put",
                productTmp, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "viewError")
    public BasicResponse<Product> view(int id) {
        return restTemplate.getForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/view?id=" + id,
                BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "Error")
    public BasicResponse<String> fetch(@RequestBody ProductTmp productTmp) {
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/warehouse/fetch",
                productTmp, BasicResponse.class);
    }

    public BasicResponse<Product> viewError(int id) {
        BasicResponse<Product> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse<String> Error(@RequestBody ProductTmp productTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }
}
