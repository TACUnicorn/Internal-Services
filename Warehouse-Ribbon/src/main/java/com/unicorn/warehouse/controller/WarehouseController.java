package com.unicorn.warehouse.controller;


import com.unicorn.warehouse.model.BasicResponse;
import com.unicorn.warehouse.model.Product;
import com.unicorn.warehouse.model.ProductTmp;
import com.unicorn.warehouse.service.WarehouseService;
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

    @RequestMapping(value = "/warehouse/put", method = RequestMethod.POST)
    public BasicResponse<String> add(@RequestBody ProductTmp productTmp) {
        return warehouseService.put(productTmp);
    }

    @RequestMapping("/warehouse/view")
    public BasicResponse<Product> view(@RequestParam int id) {
        return warehouseService.view(id);
    }

    @RequestMapping(value = "/warehouse/fetch", method = RequestMethod.POST)
    public BasicResponse<String> fetch(@RequestBody ProductTmp productTmp) {
        return warehouseService.fetch(productTmp);
    }
}
