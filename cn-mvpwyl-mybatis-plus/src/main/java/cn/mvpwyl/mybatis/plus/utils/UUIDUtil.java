package cn.mvpwyl.mybatis.plus.utils;

import java.util.UUID;

/**
 * @author wangyuliang
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
