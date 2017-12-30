package com.unicorn.finance.controller;

import com.unicorn.finance.mapper.BillMapper;
import com.unicorn.finance.model.BasicResponse;
import com.unicorn.finance.model.Bill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class BillController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    BillMapper billMapper;

    @RequestMapping(value = "/finance/transfer", method = RequestMethod.POST)
    public BasicResponse<String> transfer(@RequestBody Bill bill) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            billMapper.addBill(bill.getFromAccount(), bill.getToAccount(),
                    bill.getSum());
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(500);
            response.setMessage("error");
            response.setContent("1");
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        response.setContent("0");
        return response;
    }

    @RequestMapping(value = "/finance/bill", method = RequestMethod.GET)
    public BasicResponse<List<Bill>> view(@RequestParam Timestamp start,
                                          @RequestParam Timestamp end) {
        BasicResponse<List<Bill>> response = new BasicResponse<>();
        List<Bill> bills;
        try {
            bills = billMapper.getBill(start, end);
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(500);
            response.setMessage("error");
            response.setContent(null);
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        response.setContent(bills);
        return response;
    }
}
