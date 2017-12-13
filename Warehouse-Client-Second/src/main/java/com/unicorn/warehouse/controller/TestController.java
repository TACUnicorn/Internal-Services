package com.unicorn.warehouse.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class TestController {
    private final Logger logger = Logger.getLogger(getClass());


    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        Integer r = a + b;
        logger.info("/add, host:" + ", service_id:" + ", result:" + r);
        return r;
    }


    @Value("${server.port}")
    String port;
    @RequestMapping("/hello")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" +port;
    }
}
