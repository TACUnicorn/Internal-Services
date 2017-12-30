package com.unicorn.finance.controller;

import com.unicorn.finance.mapper.OrderMapper;
import com.unicorn.finance.model.BasicResponse;
import com.unicorn.finance.model.Order;
import com.unicorn.finance.model.OrderTmp;
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
    OrderMapper orderMapper;

    @RequestMapping(value = "/factory/place", method = RequestMethod.POST)
    public BasicResponse<String> place(@RequestBody OrderTmp orderTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        if (orderTmp.getNum() < 0) {
            response.setCode(500);
            response.setMessage("");
            response.setContent("num is lower than 0");
            return response;
        }
        try {
            // get count from database
            int count = orderMapper.addOrder(orderTmp.getId(), orderTmp.getNum());
            response.setContent(count + "");
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(404);
            response.setMessage("");
            response.setContent("error");
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

    @RequestMapping(value = "/factory/check", method = RequestMethod.GET)
    public BasicResponse<Order> view(@RequestParam int id) {
        BasicResponse<Order> response = new BasicResponse<>();
        try {
            // Get product from database
            Order order = orderMapper.getOrder(id);
            if (order != null) {
                response.setCode(200);
                response.setMessage("success");
                response.setContent(order);
                return response;
            } else {
                response.setCode(300);
                response.setMessage("not found the order");
                response.setContent(null);
                return response;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(500);
            response.setMessage("error");
            return response;
        }
    }
}
