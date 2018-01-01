package com.unicorn.server.controller;

import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Order;
import com.unicorn.server.model.OrderTmp;
import com.unicorn.server.service.FactoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class FactoryController {

    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    FactoryService factoryService;

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public BasicResponse<String> place(@RequestBody OrderTmp orderTmp) {
       return factoryService.place(orderTmp);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public BasicResponse<Order> check(@RequestParam int id) {
       return factoryService.check(id);
    }
}
