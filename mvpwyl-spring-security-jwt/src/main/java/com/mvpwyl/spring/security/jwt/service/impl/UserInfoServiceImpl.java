package com.mvpwyl.spring.security.jwt.service.impl;

import com.mvpwyl.spring.security.jwt.entity.Menu;
import com.mvpwyl.spring.security.jwt.entity.RoleMenu;
import com.mvpwyl.spring.security.jwt.entity.UserInfo;
import com.mvpwyl.spring.security.jwt.entity.UserRole;
import com.mvpwyl.spring.security.jwt.mapper.MenuMapper;
import com.mvpwyl.spring.security.jwt.mapper.RoleMenuMapper;
import com.mvpwyl.spring.security.jwt.mapper.UserInfoMapper;
import com.mvpwyl.spring.security.jwt.mapper.UserRoleMapper;
import com.mvpwyl.spring.security.jwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void register() {
        //注册,插入用户表
        UserInfo userInfo = new UserInfo();
        //生成ID:UUID,SNOW.....
        userInfo.setId(2);
        userInfo.setAccount("user");
        String password = bCryptPasswordEncoder.encode("654321");
        userInfo.setPassword(password);
        userInfo.setRoleId(2);
        userInfoMapper.insertSelective(userInfo);
        //角色
        UserRole userRole = new UserRole();
        userRole.setUserId(2);
        userRole.setRoleId(2);
        userRoleMapper.insertSelective(userRole);
        //菜单
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (i % 2 == 0) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(i);
                roleMenu.setRoleId(2);
                roleMenus.add(roleMenu);
            }

        }
        roleMenuMapper.insertList(roleMenus);
    }

    @Override
    public Map<String, Object> login(String account, String password) {
        //用户输入的密码是明文密码
        //先通过账号去数据库查询
        Map<String, Object> result = new HashMap<>();
        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("account", account);
        UserInfo userInfo = userInfoMapper.selectOneByExample(example);
        if (userInfo != null && bCryptPasswordEncoder.matches(password, userInfo.getPassword())) {
            result.put("userInfo", userInfo);
            //获取该用户对应的菜单
            List<Menu> menus = getMenuByUserId(userInfo.getId());
            result.put("menus", menus);
        } else {
            result.put("error", "您的用户名或密码有误");
        }

        return result;
    }

    private List<Menu> getMenuByUserId(Integer id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        Integer roleId = userInfo.getRoleId();
        //根据角色ID获取菜单ID
        Example example = new Example(RoleMenu.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectByExample(example);
        //根据角色菜单表中的菜单ID获取菜单列表
        List<Menu> menus = new ArrayList<>();
        if (roleMenus != null && roleMenus.size() > 0) {
            for (RoleMenu roleMenu : roleMenus) {
                //查菜单表
                Menu menu = menuMapper.selectByPrimaryKey(roleMenu.getMenuId());
                menus.add(menu);
            }
        }

        return menus;
    }
}
