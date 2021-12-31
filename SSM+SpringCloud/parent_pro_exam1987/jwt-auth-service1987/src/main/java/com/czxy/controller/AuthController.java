package com.czxy.controller;

import com.czxy.BaseResult;
import com.czxy.User;
import com.czxy.UserFeign;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private UserFeign userFeign;

    @PostMapping
    public BaseResult login(@RequestBody User user){
        BaseResult login = userFeign.login(user);
        return login;
    }
}
