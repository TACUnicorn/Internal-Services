package com.unicorn.server.service;

import com.unicorn.server.model.BasicResponse;
import com.unicorn.server.model.ProductTmp;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Create by xuantang
 * @date on 1/11/18
 */
@Service
public class FactoryService {


    @Autowired
    RestTemplate restTemplate;

    public void put(ProductTmp productTmp) {
        restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/product/put",
                productTmp, BasicResponse.class);
    }
    /**
     *
     * @param id
     * @param num
     */
    public void putWarehouse(int id, int num) {

        ProductTmp productTmp = new ProductTmp();
        productTmp.setId(id);
        productTmp.setNum(num);
        put(productTmp);
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        String content = "{\"id\":"+ id +
//                ",\"num\":" + num + "}";
//        RequestBody requestBody = RequestBody.create(
//                MediaType.parse("application/json; charset=utf-8"), content);
//        Request request = new Request.Builder()
//                .url("http://locallhost:8080/warehouse/product/put")
//                .header("Content-Type", "application/json")
//                .post(requestBody)
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//
//        try {
//            Response response = call.execute();
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
