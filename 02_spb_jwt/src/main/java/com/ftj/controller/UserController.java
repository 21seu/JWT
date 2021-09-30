package com.ftj.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ftj.entity.User;
import com.ftj.service.UserService;
import com.ftj.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengtj on 2021/9/28 23:46
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {
        log.info("用户名：[{}] 密码：[{}]", user.getUsername(), user.getPassword());
        Map<String, Object> map = new HashMap<>();
        try {
            User loginDB = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            map.put("id", loginDB.getId());
            map.put("username", loginDB.getUsername());
            //生成jwt令牌
            String token = JWTUtil.getToken(payload);
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", "认证失败");
        }
        return map;
    }

    @PostMapping("/user/test")
    public Map<String, Object> test(String token) {
        Map<String, Object> map = new HashMap<>();
        //代码改造，处理业务逻辑，改造前代码，看上一个commit
        map.put("state", true);
        map.put("msg", "请求成功！");
        return map;
    }
}
