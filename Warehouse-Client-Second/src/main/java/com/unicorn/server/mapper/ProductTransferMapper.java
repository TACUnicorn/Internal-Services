package com.unicorn.server.mapper;

import com.unicorn.server.model.ProductTransfer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 1/9/18
 */
@Mapper
@Service
public interface ProductTransferMapper {

    @Select("SELECT product_transfer.id, product.id AS p_id, num, name, time, type, state FROM product_transfer, product" +
            " WHERE p_id = product.id && time > #{start} && time < #{end} && state = #{state}")
    List<ProductTransfer> getProductTransfers(@Param("start") Timestamp start,
                                              @Param("end") Timestamp end,
                                              @Param("state") int state);

    @Select("SELECT product_transfer.id, product.id AS p_id, num, name, time, type, state FROM product_transfer, product" +
            " WHERE p_id = product.id && state = #{state}")
    List<ProductTransfer> getProductTransfersNoDate(@Param("state") int state);

    @Insert("INSERT INTO product_transfer(p_id, num, time, type, state) " +
            "VALUES(#{p_id}, #{num}, #{time}, #{type}, #{state})")
    int addProductTransfers(ProductTransfer productTransfer);

    @Update("UPDATE product_transfer SET state = #{state} WHERE id = #{id}")
    void updateProductTransfer(@Param("id") int id,
                               @Param("state") int state);
}
