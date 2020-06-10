package com.mvpwyl.spring.security.jwt.service;

import java.util.Map;

/**
 * @author wangyuliang
 * 用户服务接口
 */
public interface UserInfoService {

    void register();

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    Map<String,Object> login(String account,String password);

}
