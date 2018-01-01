package com.unicorn.server.controller;


import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.Bill;
import com.unicorn.server.service.FinanceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class BillController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    FinanceService financeService;

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public BasicResponse<String> transfer(@RequestBody Bill bill) {
        return financeService.transfer(bill);
    }

    @RequestMapping(value = "/bill", method = RequestMethod.GET)
    public BasicResponse<List<Bill>> view(@RequestParam Timestamp start,
                                          @RequestParam Timestamp end) {
        return financeService.bill(start, end);
    }
}
