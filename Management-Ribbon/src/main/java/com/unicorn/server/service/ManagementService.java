package com.unicorn.server.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.UTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@Service
public class ManagementService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "Error")
    public BasicResponse<String> login(@RequestBody UTmp uTmp) {
        return restTemplate.postForObject("http://MANAGEMENT-SERVICE-CLIENT/authentication",
                uTmp, BasicResponse.class);
    }

    public BasicResponse<String> Error(@RequestBody UTmp uTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }
}
