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
        log.info("当前token：【{}】", token);
        try {
            DecodedJWT verify = JWTUtil.verify(token);
            map.put("state", true);
            map.put("msg", "请求成功！");
            return map;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名！");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token已过期！");
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "算法不一致！");
            e.printStackTrace();
        } catch (Exception e) {
            map.put("msg", "token无效！");
            e.printStackTrace();
        }
        map.put("state", false);
        return map;
    }
}
