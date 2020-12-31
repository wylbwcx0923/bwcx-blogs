package com.sugon.gridview.security.entity.vo;

import com.sugon.gridview.security.entity.GvUser;

public class GvUserRoleVO {
    private GvUser gvUser;
    private String[] roleIds;

    public GvUser getGvUser() {
        return gvUser;
    }

    public void setGvUser(GvUser gvUser) {
        this.gvUser = gvUser;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}
