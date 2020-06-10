package com.mvpwyl.spring.security.jwt.controller;

import com.mvpwyl.spring.security.jwt.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserInfoController {

    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;


    @PostMapping(value = "login")
    public Map<String,Object> login(@RequestParam(value = "account",required = true)String account,
                                    @RequestParam(value = "password",required = true)String password){
        return userInfoService.login(account, password);
    }
}
