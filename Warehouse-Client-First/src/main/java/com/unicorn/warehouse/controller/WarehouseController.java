package com.unicorn.warehouse.controller;

import com.unicorn.warehouse.mapper.ProductMapper;
import com.unicorn.warehouse.mapper.WarehouseMapper;
import com.unicorn.warehouse.model.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class WarehouseController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    WarehouseMapper warehouseMapper;

    @Autowired
    ProductMapper productMapper;

    @RequestMapping(value = "/warehouse/put", method = RequestMethod.POST)
    public String put(@RequestParam int id, @RequestParam int num) {
        if (num < 0) {
            return "500";
        }
        try {
            // get count from database
            int count = warehouseMapper.getProductCountFromWarehouse(id);
            System.out.println(count);
            if ( count > 0) {
                warehouseMapper.updateWarehouse(id, num + count);
            } else {
                warehouseMapper.addWarehouse(id, count);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return "404";
        }
        return "200";
    }

    @RequestMapping(value = "/warehouse/view", method = RequestMethod.GET)
    public Product view(@RequestParam int id) {
        try {
            // get product from database
            Product product = productMapper.getProductFromWarehouseByID(id);
            if ( product != null) {
                return product;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
        return null;
    }

    @RequestMapping(value = "/warehouse/fetch", method = RequestMethod.POST)
    public String fetch(@RequestParam int id, @RequestParam int num) {
        if (num < 0) {
            return "500";
        }
        try {
            // get count from database
            int count = warehouseMapper.getProductCountFromWarehouse(id);
            if ( count > num) {
                warehouseMapper.updateWarehouse(id, count - num);
            }
            return "success";
        } catch (Exception e) {
            logger.info(e.getMessage());
            return "404";
        }
    }
}
