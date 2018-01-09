package com.unicorn.server.mapper;

import com.unicorn.server.model.Product;
import com.unicorn.server.model.ProductInfoTmp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface ProductMapper {

    @Select("SELECT product.id, name, description, price, num FROM product, warehouse " +
            "WHERE product.id = warehouse.p_id AND id = #{id}")
    Product getProductFromWarehouseByID(int id);

    @Insert("INSERT INTO product(name, price, description) VALUES (#{name}, #{price}, #{description})")
    int addProduct(ProductInfoTmp productInfoTmp);

    @Update("UPDATE product SET name = #{name}, price = #{price}, " +
            "description = #{description} WHERE id = #{product_id}")
    void updateProduct(@Param("name") String name,
                       @Param("price") int price,
                       @Param("description") String description,
                       @Param("product_id") int product_id);
}
