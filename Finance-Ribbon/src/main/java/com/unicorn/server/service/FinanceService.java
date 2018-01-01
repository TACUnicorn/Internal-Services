package com.unicorn.server.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@Service
public class FinanceService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "Error")
    public BasicResponse<String> transfer(@RequestBody Bill bill) {
        return restTemplate.postForObject("http://FINANCE-SERVICE-CLIENT/transfer",
                bill, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "viewError")
    public BasicResponse<List<Bill>> bill(Timestamp start, Timestamp end) {
        return restTemplate.getForObject("http://FINANCE-SERVICE-CLIENT/bill?start="
                        + start + "&end=" + end, BasicResponse.class);
    }


    public BasicResponse<List<Bill>> viewError(Timestamp start, Timestamp end) {
        BasicResponse<List<Bill>> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse<String> Error(@RequestBody Bill bill) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }
}
