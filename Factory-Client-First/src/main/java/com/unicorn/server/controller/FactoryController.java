package com.unicorn.server.controller;

import com.unicorn.server.mapper.OrderMapper;
import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Order;
import com.unicorn.server.model.OrderTmp;
import com.unicorn.server.service.FactoryService;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class FactoryController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    FactoryService factoryService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
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

    @RequestMapping(value = "/check", method = RequestMethod.GET)
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

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public BasicResponse<List<Order>> view() {
        BasicResponse<List<Order>> response = new BasicResponse<>();
        try {
            // Get product from database
            List<Order> orders = orderMapper.getOrders();
            if (orders != null) {
                response.setCode(200);
                response.setMessage("success");
                response.setContent(orders);
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

    @RequestMapping(value = "/order", method = RequestMethod.PUT)
    public BasicResponse<String> update(@RequestBody Order order) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            // Get product from database
            orderMapper.updateOrder(order);
            if (order.getState() == 1) {
                factoryService.putWarehouse(order.getP_id(), order.getNum());
            }
            response.setCode(200);
            response.setMessage("success");
            return response;
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(500);
            response.setMessage("error");
            return response;
        }
    }
}
