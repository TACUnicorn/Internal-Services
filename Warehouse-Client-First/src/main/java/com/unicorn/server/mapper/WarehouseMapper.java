package com.unicorn.server.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface WarehouseMapper {

    @Insert("INSERT INTO warehouse(p_id, num) VALUES (#{p_id}, #{num})")
    void addWarehouse(@Param("p_id") int p_id, @Param("num")int num);

    @Update("UPDATE warehouse SET num = #{num} WHERE p_id = #{p_id}")
    void updateWarehouse(@Param("p_id") int p_id, @Param("num")int num);

    @Select("SELECT num FROM warehouse WHERE p_id = #{p_id}")
    int getProductCountFromWarehouse(int p_id);

    @Select("select count(*) from warehouse where p_id = #{p_id}")
    int checkProduct(int p_id);


}
