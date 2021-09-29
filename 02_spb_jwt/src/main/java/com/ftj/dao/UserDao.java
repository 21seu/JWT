package com.ftj.dao;

import com.ftj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by fengtj on 2021/9/28 23:41
 */
@Mapper
public interface UserDao {

    @Select("select * from user where name=#{user.name} and password=#{user.password}")
    User login(@Param("user") User user);
}
