package com.unicorn.warehouse.controller;

import com.unicorn.warehouse.model.BasicResponse;
import com.unicorn.warehouse.model.Order;
import com.unicorn.warehouse.model.OrderTmp;
import com.unicorn.warehouse.service.FactoryService;
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

    @RequestMapping(value = "/factory/place", method = RequestMethod.POST)
    public BasicResponse<String> place(@RequestBody OrderTmp orderTmp) {
       return factoryService.place(orderTmp);
    }

    @RequestMapping(value = "/factory/check", method = RequestMethod.GET)
    public BasicResponse<Order> check(@RequestParam int id) {
       return factoryService.check(id);
    }
}
