package com.unicorn.server.mapper;

import com.unicorn.server.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface OrderMapper {

    @Insert("INSERT INTO orders(p_id, num) VALUES (#{id}, #{num})")
    int addOrder(@Param("id") int id, @Param("num") int num);

    @Update("UPDATE orders SET p_id = #{p_id}, num = #{num}, state = #{state}," +
            "time = #{time} WHERE id = #{id}")
    void updateOrder(Order order);

    @Select("SELECT id, p_id, time, num, state FROM orders\n" +
            "WHERE id = #{id}")
    Order getOrder(@Param("id") int id);

    @Select("SELECT id, p_id, time, num, state FROM orders")
    List<Order> getOrders();

    @Select("SELECT count(*) FROM material\n" +
            "WHERE id = #{id}")
    int getMaterialCount(@Param("id") int id);

}
