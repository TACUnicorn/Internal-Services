package com.unicorn.server.controller;


import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Product;
import com.unicorn.server.model.ProductTmp;
import com.unicorn.server.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public BasicResponse<String> add(@RequestBody ProductTmp productTmp) {
        return warehouseService.put(productTmp);
    }

    @RequestMapping("/view")
    public BasicResponse<Product> view(@RequestParam int id) {
        return warehouseService.view(id);
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public BasicResponse<String> fetch(@RequestBody ProductTmp productTmp) {
        return warehouseService.fetch(productTmp);
    }
}
