package com.sugon.gridview.security.service.impl;

import com.sugon.gridview.security.entity.GvMenuRole;
import com.sugon.gridview.security.entity.GvRole;
import com.sugon.gridview.security.entity.GvUser;
import com.sugon.gridview.security.entity.GvUserRole;
import com.sugon.gridview.security.entity.vo.GvUserRoleVO;
import com.sugon.gridview.security.mapper.*;
import com.sugon.gridview.security.service.UserService;
import com.sugon.gridview.security.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author wangyl
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private GvUserMapper gvUserMapper;
    @Autowired
    private GvUserRoleMapper gvUserRoleMapper;
    @Autowired
    private GvRoleMapper gvRoleMapper;
    @Autowired
    private GvMenuMapper gvMenuMapper;
    @Autowired
    private GvMenuRoleMapper gvMenuRoleMapper;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void regist(GvUserRoleVO gvUserRoleVO) {
        GvUser gvUser = gvUserRoleVO.getGvUser();
        String userId = UUIDUtil.getUUID();
        gvUser.setId(userId);
        gvUser.setCreateTime(new Date());
        gvUser.setUpdateTime(new Date());
        //密码加密
        String password = DigestUtils.md5DigestAsHex(gvUser.getPassword().getBytes());
        gvUser.setPassword(password);
        gvUserMapper.insert(gvUser);
        //将用户角色信息存入数据库
        String[] roleIds = gvUserRoleVO.getRoleIds();
        List<GvUserRole> gvUserRoleList = new ArrayList<>();
        for (String roleId : roleIds) {
            GvUserRole gvUserRole = new GvUserRole();
            gvUserRole.setId(UUIDUtil.getUUID());
            gvUserRole.setUserId(userId);
            gvUserRole.setRoleId(roleId);
        }
        gvUserRoleMapper.insertList(gvUserRoleList);
    }

    @Override
    public Map<String, Object> login(String account, String password) {
        //先通过账号去查询
        Example exampleUser = new Example(GvUser.class);
        exampleUser.createCriteria().andEqualTo("account", account);
        GvUser gvUser = gvUserMapper.selectOneByExample(exampleUser);
        if (gvUser == null) {
            new RuntimeException("登录失败，用户名不存在");
        }
        //程序执行至此,说明账号存在,下面开始验证密码
        //1.将传来的密码进行MD5
        String passwordReal = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.进行比对
        if (!passwordReal.equals(gvUser.getPassword())) {
            new RuntimeException("登录失败，密码错误");
        }
        //程序执行至此说明登录成功,封装一些参数,将用户信息和菜单以及角色信息返回
        Map<String, Object> result = new HashMap<>();
        result.put("gvUser", gvUser);
        //获得角色信息,获取菜单信息
        Example exampleUserRole = new Example(GvUserRole.class);
        exampleUserRole.createCriteria().andEqualTo("userId", gvUser.getId());
        List<GvUserRole> gvUserRoles = gvUserRoleMapper.selectByExample(exampleUserRole);
        //角色信息
        List<GvRole> gvRoles = new ArrayList<>();
        //菜单信息
        Set<GvMenuRole> gvMenuRoles=new HashSet<>();
        for (GvUserRole gvUserRole : gvUserRoles) {
            GvRole gvRole = gvRoleMapper.selectByPrimaryKey(gvUserRole.getRoleId());
            gvRoles.add(gvRole);
            Example exampleMenuRole=new Example(GvMenuRole.class);
            exampleMenuRole.createCriteria().andEqualTo("roleId",gvUserRole.getRoleId());
        }
        result.put("gvRole", gvRoles);


        return null;
    }
}
