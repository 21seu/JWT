package com.ftj.service.impl;

import com.ftj.dao.UserDao;
import com.ftj.entity.User;
import com.ftj.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by fengtj on 2021/9/28 23:44
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        User userDB = userDao.login(user);
        if (userDB != null) return userDB;
        throw new RuntimeException("登录失败~~~~");
    }
}
