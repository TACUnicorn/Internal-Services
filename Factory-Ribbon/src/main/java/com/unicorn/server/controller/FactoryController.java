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

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public BasicResponse place(@RequestBody OrderTmp orderTmp) {
       return factoryService.place(orderTmp);
    }

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    public BasicResponse place(@RequestBody Order order) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            factoryService.updateOrder(order);
            response.setCode(200);
            response.setMessage("success");
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public BasicResponse view() {
       return factoryService.getOrders();
    }
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public BasicResponse check(@RequestParam int id) {
       return factoryService.check(id);
    }
}
