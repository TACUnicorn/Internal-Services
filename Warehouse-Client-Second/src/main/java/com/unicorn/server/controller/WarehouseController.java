package com.unicorn.server.controller;

import com.unicorn.server.mapper.ProductMapper;
import com.unicorn.server.mapper.ProductTransferMapper;
import com.unicorn.server.mapper.WarehouseMapper;
import com.unicorn.server.model.*;
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
public class WarehouseController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    WarehouseMapper warehouseMapper;

    @Autowired
    ProductTransferMapper productTransferMapper;

    @Autowired
    ProductMapper productMapper;

    @RequestMapping(value = "/product/put", method = RequestMethod.POST)
    public BasicResponse<String> put(@RequestBody ProductTmp productTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        if (productTmp.getNum() < 0) {
            response.setCode(500);
            response.setMessage("");
            response.setContent("num is lower than 0");
            return response;
        }
        try {
            // get count from database
            if (warehouseMapper.checkProduct(productTmp.getId()) > 0) {
                int count = warehouseMapper.getProductCountFromWarehouse(productTmp.getId());
                if (count > 0) {
                    warehouseMapper.updateWarehouse(productTmp.getId(), productTmp.getNum() + count);
                }
            } else {
                warehouseMapper.addWarehouse(productTmp.getId(), productTmp.getNum());
            }
            // product record
            ProductTransfer productTransfer = new ProductTransfer();
            productTransfer.setNum(productTmp.getNum());
            productTransfer.setP_id(productTmp.getId());
            productTransfer.setType(0);
            productTransfer.setState(0);
            productTransfer.setTime(new Timestamp(System.currentTimeMillis()));
            productTransferMapper.addProductTransfers(productTransfer);
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(404);
            response.setMessage("");
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        response.setContent("true");
        return response;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public BasicResponse<Product> view(@RequestParam int id) {
        BasicResponse<Product> response = new BasicResponse<>();
        try {
            // Get product from database
            Product product = productMapper.getProductFromWarehouseByID(id);
            if (product != null) {
                response.setCode(200);
                response.setMessage("success");
                response.setContent(product);
                return response;
            } else {
                response.setCode(300);
                response.setMessage("not found the product");
                response.setContent(null);
                return response;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(500);
            response.setMessage("error");
            return response;
        }
    }

    @RequestMapping(value = "/product/fetch", method = RequestMethod.POST)
    public BasicResponse<String> fetch(@RequestBody ProductTmp productTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        if (productTmp.getNum() < 0) {
            response.setCode(500);
            response.setMessage("");
            response.setContent("num is lower than 0");
            return response;
        }
        try {
            // get count from database
            int count = warehouseMapper.getProductCountFromWarehouse(productTmp.getId());
            if (count > productTmp.getNum()) {
                warehouseMapper.updateWarehouse(productTmp.getId(), count - productTmp.getNum());
            }
            // product record
            ProductTransfer productTransfer = new ProductTransfer();
            productTransfer.setNum(productTmp.getNum());
            productTransfer.setP_id(productTmp.getId());
            productTransfer.setType(1);
            productTransfer.setState(0);
            productTransfer.setTime(new Timestamp(System.currentTimeMillis()));
            productTransferMapper.addProductTransfers(productTransfer);
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(404);
            response.setMessage("error");
            return response;
        }
        response.setCode(200);
        response.setMessage("success");
        response.setContent("");
        return response;
    }

    @PutMapping(value = "/product/{product_id}")
    public BasicResponse<String> updateProduct(@PathVariable("product_id") int productId,
                                               @RequestBody ProductInfoTmp productInfoTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            productMapper.updateProduct(productInfoTmp.getName(), productInfoTmp.getPrice(),
                    productInfoTmp.getDescription(), productId);
            response.setCode(200);
            response.setMessage("success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }

    @PutMapping(value = "/product/transfer/{transfer_id}")
    public BasicResponse<String> addProduct(@PathVariable("transfer_id") int id,
                                            @RequestParam("state") int state) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            productTransferMapper.updateProductTransfer(id, state);
            response.setCode(200);
            response.setMessage("success");
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }

    @GetMapping(value = "/product/transfers")
    public BasicResponse<List<ProductTransfer>> getProductTransfers(@RequestParam("start") Timestamp start,
                                                                    @RequestParam("end") Timestamp end,
                                                                    @RequestParam(value = "state") int state) {
        BasicResponse<List<ProductTransfer>> response = new BasicResponse<>();
        try {
            List<ProductTransfer> productTransfers = productTransferMapper.getProductTransfers(start, end, state);
            response.setCode(200);
            response.setMessage("success");
            response.setContent(productTransfers);
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping(value = "/product")
    public BasicResponse<String> addProduct(@RequestBody ProductInfoTmp productInfoTmp) {
        BasicResponse<String> response = new BasicResponse<>();
        try {
            productMapper.addProduct(productInfoTmp);
            response.setCode(200);
            response.setMessage("success");
        } catch (Exception e) {
            response.setCode(400);
            response.setMessage("fail");
        }
        return response;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public BasicResponse<List<Product>> getProducts() {
        BasicResponse<List<Product>> response = new BasicResponse<>();
        try {
            // Get product from database
            List<Product> products = warehouseMapper.getProductsFromWarehouse();
            if (products != null) {
                response.setCode(200);
                response.setMessage("success");
                response.setContent(products);
                return response;
            } else {
                response.setCode(300);
                response.setMessage("no products");
                response.setContent(null);
                return response;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            response.setCode(500);
            response.setMessage("error");
            return response;
        }
    }
}
