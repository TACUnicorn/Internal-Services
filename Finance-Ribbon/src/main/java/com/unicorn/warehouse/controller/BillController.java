package com.unicorn.warehouse.controller;


import com.unicorn.warehouse.model.BasicResponse;
import com.unicorn.warehouse.model.Bill;
import com.unicorn.warehouse.service.FinanceService;
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

    @RequestMapping(value = "/finance/transfer", method = RequestMethod.POST)
    public BasicResponse<String> transfer(@RequestBody Bill bill) {
        return financeService.transfer(bill);
    }

    @RequestMapping(value = "/finance/bill", method = RequestMethod.GET)
    public BasicResponse<List<Bill>> view(@RequestParam Timestamp start,
                                          @RequestParam Timestamp end) {
        return financeService.bill(start, end);
    }
}
