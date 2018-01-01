package com.unicorn.server.mapper;

import com.unicorn.server.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface ProductMapper {

    @Select("SELECT product.id, name, description, num FROM product, warehouse " +
            "WHERE product.id = warehouse.p_id AND id = #{id}")
    Product getProductFromWarehouseByID(int id);
}
