package com.ftj.dao;

import com.ftj.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fengtj on 2021/9/28 23:41
 */
@Mapper
public interface UserDao {

    User login(User user);
}
