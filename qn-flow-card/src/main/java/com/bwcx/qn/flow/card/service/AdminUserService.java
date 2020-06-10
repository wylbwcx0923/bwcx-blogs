package com.bwcx.qn.flow.card.service;

import com.bwcx.qn.flow.card.entity.AdminUser;

import java.util.List;

/**
 * @author 不忘初心
 * 管理员服务
 */
public interface AdminUserService {
    //添加管理员
    boolean addAdminUser(AdminUser adminUser);

    //删除管理员
    boolean delAdminUser(int id);

    //修改管理员信息
    boolean updateAdminUser(AdminUser adminUser);

    //获取所以的管理员信息
    List<AdminUser> getAdminUserList();

    //管理员登录
    AdminUser login(String account, String password);

    //通过id查询用户
    AdminUser findUserById(Integer id);

}
