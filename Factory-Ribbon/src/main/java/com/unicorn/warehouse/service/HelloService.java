package com.unicorn.warehouse.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloError")
    public String helloService(String name) {
        return restTemplate.getForObject("http://WAREHOUSE-SERVICE-CLIENT/hello?name=" + name, String.class);
    }

    public String helloError(String name) {
        return "hi," + name + ", sorry, error!";
    }
}
