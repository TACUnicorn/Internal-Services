package com.unicorn.finance.mapper;

import com.unicorn.finance.model.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/14/17
 */
@Mapper
@Service
public interface BillMapper {

    @Select("SELECT fromAccount, toAccount, sum FROM bill WHERE date > #{start} && date < #{end}")
    List<Bill> getBill(@Param("start") Timestamp start,
                       @Param("end") Timestamp end);

    @Insert("INSERT INTO bill(fromAccount, toAccount, sum, date)\n" +
            "VALUES (#{fromAccount}, #{toAccount}, #{sum}, now())")
    void addBill(@Param("fromAccount") String fromAccount,
                 @Param("toAccount") String toAccount,
                 @Param("sum") float sum);
}
