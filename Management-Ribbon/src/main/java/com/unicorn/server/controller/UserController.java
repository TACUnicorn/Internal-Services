package com.unicorn.server.controller;


import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.UTmp;
import com.unicorn.server.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class UserController {

    @Autowired
    ManagementService managementService;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public BasicResponse<String> login(@RequestBody UTmp uTmp) {
        return managementService.login(uTmp);
    }
}
