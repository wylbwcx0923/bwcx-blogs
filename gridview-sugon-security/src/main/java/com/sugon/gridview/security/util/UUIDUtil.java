package com.sugon.gridview.security.util;

import java.util.UUID;

/**
 * @author wangyuliang
 * UUID的工具类
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
