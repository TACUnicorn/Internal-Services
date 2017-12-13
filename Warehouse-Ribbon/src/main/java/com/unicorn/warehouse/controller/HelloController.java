package com.unicorn.warehouse.controller;


import com.unicorn.warehouse.model.Product;
import com.unicorn.warehouse.service.WarehouseService;
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
public class HelloController {

    @Autowired
    WarehouseService warehouseService;

    @RequestMapping(value = "/warehouse/put", method = RequestMethod.POST)
    public String add(@RequestParam int id, @RequestParam int num) {
        return warehouseService.put(id, num);
    }

    @RequestMapping("/warehouse/view")
    public Product view(@RequestParam int id) {
        return warehouseService.view(id);
    }

    @RequestMapping(value = "/warehouse/fetch", method = RequestMethod.POST)
    public String fetch(@RequestParam int id, @RequestParam int num) {
        return warehouseService.fetch(id,num);
    }
}
