package com.unicorn.warehouse.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.warehouse.model.BasicResponse;
import com.unicorn.warehouse.model.Order;
import com.unicorn.warehouse.model.OrderTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@Service
public class FactoryService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "Error")
    public BasicResponse<String> place(@RequestBody OrderTmp orderTmp) {
        return restTemplate.postForObject("http://FACTORY-SERVICE-CLIENT/factory/place",
                orderTmp, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "viewError")
    public BasicResponse<Order> check(int id) {
        return restTemplate.getForObject("http://FACTORY-SERVICE-CLIENT/factory/check?id=" +
                id, BasicResponse.class);
    }


    public BasicResponse<Order> viewError(int id) {
        BasicResponse<Order> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse<String> Error(@RequestBody OrderTmp orderTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }
}
