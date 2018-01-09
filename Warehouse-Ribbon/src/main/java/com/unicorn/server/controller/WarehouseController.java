package com.unicorn.server.controller;


import com.unicorn.server.model.*;
import com.unicorn.server.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public BasicResponse add(@RequestBody ProductTmp productTmp) {
        return warehouseService.put(productTmp);
    }

    @RequestMapping("/view")
    public BasicResponse view(@RequestParam int id) {
        return warehouseService.view(id);
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public BasicResponse fetch(@RequestBody ProductTmp productTmp) {
        return warehouseService.fetch(productTmp);
    }

    @PutMapping(value = "/product/{product_id}")
    public BasicResponse<String> updateProduct(@PathVariable("product_id") int productId,
                                               @RequestBody ProductInfoTmp productInfoTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            warehouseService.updateProduct(productInfoTmp, productId);
            response.setCode(200);
            response.setMessage("success");
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }

    @PutMapping(value = "/product/transfer/{transfer_id}")
    public BasicResponse updateProduct(@PathVariable("transfer_id") int id,
                                       @RequestParam("state") int state) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            warehouseService.updateProductTransfer(id, state);
            response.setCode(200);
            response.setMessage("success");
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }

    @GetMapping(value = "/product/transfers")
    public BasicResponse getProductTransfers(@RequestParam("start") Timestamp start,
                                             @RequestParam("end") Timestamp end,
                                             @RequestParam(value = "state") int state) {
        return warehouseService.getProductTransfers(start, end, state);
    }

    @PostMapping(value = "/product")
    public BasicResponse addProduct(@RequestBody ProductInfoTmp productInfoTmp) {
        return warehouseService.addProduct(productInfoTmp);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public BasicResponse getProducts() {
        return warehouseService.getProducts();
    }
}


