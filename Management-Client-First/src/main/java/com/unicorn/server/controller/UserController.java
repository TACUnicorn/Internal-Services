package com.unicorn.server.controller;

import com.unicorn.server.mapper.UserMapper;
import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.UTmp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@RestController
public class UserController {
    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/authentication" ,method = RequestMethod.POST)
    public BasicResponse<String> add(@RequestBody UTmp uTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(200);
        try {
            if (userMapper.validate(uTmp.getId(), uTmp.getPassword()) > 0) {
                response.setMessage("success");
            } else {
                response.setCode(404);
                response.setMessage("fail");
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("fail");
            response.setContent(e.getMessage());
        }
        return response;
    }

}
