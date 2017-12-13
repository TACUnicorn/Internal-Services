package com.unicorn.warehouse.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface WarehouseMapper {

    @Insert("INSERT INTO warehouse(p_id, num) VALUES (#{p_id}, #{num})")
    void addWarehouse(int p_id, int num);

    @Update("UPDATE warehouse SET num = #{num} WHERE p_id = #{p_id}")
    void updateWarehouse(int p_id, int num);

    @Select("SELECT num FROM warehouse WHERE p_id = #{p_id}")
    int getProductCountFromWarehouse(int p_id);

}
