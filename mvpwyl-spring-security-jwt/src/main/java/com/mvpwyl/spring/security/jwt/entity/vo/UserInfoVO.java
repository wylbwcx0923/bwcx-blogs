package com.mvpwyl.spring.security.jwt.entity.vo;

import javax.persistence.Column;

public class UserInfoVO {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;



}
