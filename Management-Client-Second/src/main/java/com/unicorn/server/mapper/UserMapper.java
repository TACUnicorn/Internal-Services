package com.unicorn.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author Create by xuantang
 * @date on 1/1/18
 */
@Mapper
@Service
public interface UserMapper {

    @Select("SELECT count(*) FROM user\n" +
            "WHERE username = #{username} AND password = #{password}")
    int validate(@Param("username") String username,
                 @Param("password") String password);
}
