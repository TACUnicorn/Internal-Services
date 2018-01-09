package com.unicorn.server.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Order;
import com.unicorn.server.model.OrderTmp;
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
    public BasicResponse place(@RequestBody OrderTmp orderTmp) {
        return restTemplate.postForObject("http://FACTORY-SERVICE-CLIENT/order",
                orderTmp, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "updateError")
    public void updateOrder(@RequestBody Order order) {
        restTemplate.put("http://FACTORY-SERVICE-CLIENT/order", order);
    }

    @HystrixCommand(fallbackMethod = "getOrdersError")
    public BasicResponse getOrders() {
        return restTemplate.getForObject("http://FACTORY-SERVICE-CLIENT/orders",
                BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "viewError")
    public BasicResponse check(int id) {
        return restTemplate.getForObject("http://FACTORY-SERVICE-CLIENT/check?id=" +
                id, BasicResponse.class);
    }

    public BasicResponse getOrdersError() {
        BasicResponse response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse<Order> viewError(int id) {
        BasicResponse<Order> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public void updateError(@RequestBody Order order) {
    }

    public BasicResponse<String> Error(@RequestBody OrderTmp orderTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }
}
