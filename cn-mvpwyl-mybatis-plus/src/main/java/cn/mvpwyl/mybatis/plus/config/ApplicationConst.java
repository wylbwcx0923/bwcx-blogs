package cn.mvpwyl.mybatis.plus.config;

/**
 * 系统常量
 *
 * @author wangyuliang
 * @date
 */
public class ApplicationConst {

    public static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    /**
     * 权限相关
     */
    public static class Permissions {
        public static final String SUPER_ADMIN_ID = "885d3cab1e7d41dd8fe78de0786b4b2f";
        public static final int TOKEN_PREIX_COUNT = 7;
        public static final String TOKEN_PREIX = "Bearer ";
        public static final String HEADER_NAME = "Authorization";
    }

}
