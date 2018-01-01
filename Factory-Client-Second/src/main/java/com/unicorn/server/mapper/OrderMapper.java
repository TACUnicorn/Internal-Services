package com.unicorn.server.mapper;

import com.unicorn.server.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface OrderMapper {

    @Insert("INSERT INTO orders(p_id, num) VALUES (#{id}, #{num})")
    int addOrder(@Param("id") int id, @Param("num") int num);

    @Update("UPDATE orders SET progress = #{progress} WHERE id = #{id}")
    void updateOrder(@Param("id") int id,
                     @Param("progress") int progress);
    @Select("SELECT id, p_id, progress, num FROM orders\n" +
            "WHERE id = #{id}")
    Order getOrder(@Param("id") int id);

    @Select("SELECT count(*) FROM material\n" +
            "WHERE id = #{id}")
    int getMaterialCount(@Param("id") int id);

}
