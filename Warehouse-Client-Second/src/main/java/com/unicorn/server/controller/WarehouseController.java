package com.unicorn.server.controller;

import com.unicorn.server.mapper.ProductMapper;
import com.unicorn.server.mapper.WarehouseMapper;
import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Product;
import com.unicorn.server.model.ProductTmp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public BasicResponse<String> put(@RequestBody ProductTmp productTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        if (productTmp.getNum() < 0) {
            response.setCode(500);
            response.setMessage("");
            response.setContent("num is lower than 0");
            return response;
        }
        try {
            // get count from database
            if (warehouseMapper.checkProduct(productTmp.getId()) > 0) {
                int count = warehouseMapper.getProductCountFromWarehouse(productTmp.getId());
                if (count > 0) {
                    warehouseMapper.updateWarehouse(productTmp.getId(), productTmp.getNum() + count);
                }
            } else {
                warehouseMapper.addWarehouse(productTmp.getId(), productTmp.getNum());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.info(e.getMessage());
            response.setCode(404);
            response.setMessage("");
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        response.setContent("true");
        return response;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public BasicResponse<Product> view(@RequestParam int id) {
        BasicResponse<Product> response = new BasicResponse<>();
        try {
            // Get product from database
            Product product = productMapper.getProductFromWarehouseByID(id);
            if (product != null) {
                response.setCode(200);
                response.setMessage("success");
                response.setContent(product);
                return response;
            } else {
                response.setCode(300);
                response.setMessage("not found the product");
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

    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public BasicResponse<String> fetch(@RequestBody ProductTmp productTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        if (productTmp.getNum() < 0) {
            response.setCode(500);
            response.setMessage("");
            response.setContent("num is lower than 0");
            return response;
        }
        try {
            // get count from database
            int count = warehouseMapper.getProductCountFromWarehouse(productTmp.getId());
            if ( count > productTmp.getNum()) {
                warehouseMapper.updateWarehouse(productTmp.getId(), count - productTmp.getNum());
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(404);
            response.setMessage("error");
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        response.setContent("");
        return response;
    }
}
