package com.unicorn.server.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.unicorn.server.model.*;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/13/17
 */
@Service
public class WarehouseService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "postError")
    public BasicResponse put(@RequestBody ProductTmp productTmp) {
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/put",
                productTmp, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "getProductsError")
    public BasicResponse getProducts() {
        return restTemplate.getForObject("http://WAREHOUSE-SERVICE-CLIENT/products", BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "putError")
    public void updateProduct(@RequestBody ProductInfoTmp productInfoTmp,
                                               int product_id) {
        restTemplate.put("http://WAREHOUSE-SERVICE-CLIENT/product/" + product_id, productInfoTmp);
    }

    @HystrixCommand(fallbackMethod = "Error")
    public BasicResponse addProduct(@RequestBody ProductInfoTmp productInfoTmp) {
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/product", productInfoTmp, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "getProductTransferError")
    public BasicResponse getProductTransfers(Timestamp start, Timestamp end, int state) {
        return restTemplate.getForObject("http://WAREHOUSE-SERVICE-CLIENT/product/transfer?start=" + start + "&end=" + end
                + "&state=" + state, BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "viewError")
    public BasicResponse view(int id) {
        return restTemplate.getForObject("http://WAREHOUSE-SERVICE-CLIENT/view?id=" + id,
                BasicResponse.class);
    }

    @HystrixCommand(fallbackMethod = "updateProductTransferError")
    public void updateProductTransfer(int id, int state) {
        restTemplate.put("http://WAREHOUSE-SERVICE-CLIENT/product/transfer/" + id, state);
    }

    @HystrixCommand(fallbackMethod = "postError")
    public BasicResponse fetch(@RequestBody ProductTmp productTmp) {
        return restTemplate.postForObject("http://WAREHOUSE-SERVICE-CLIENT/fetch",
                productTmp, BasicResponse.class);
    }

    public BasicResponse viewError(int id) {
        BasicResponse<Product> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse getProductsError() {
        BasicResponse<List<Product>> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse getProductTransferError() {
        BasicResponse<List<ProductTransfer>> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public BasicResponse postError(@RequestBody ProductTmp productTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        response.setCode(400);
        response.setMessage("error");
        return response;
    }

    public void updateProductTransferError(int id, int state) {
    }
    public void putError(@RequestBody ProductInfoTmp productInfoTmp,
                         int product_id) {
    }
}
