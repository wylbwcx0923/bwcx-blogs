package com.bwcx.qn.flow.card.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 不忘初心
 * web拦截器的配置类
 */
@Configuration
public class QNWebInterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private QNWebInterceptor qnWebInterceptor;

    /**
     * 重写方法添加拦截的路径
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截项目中的所有访问,但是登录不拦截
        //全体用户可以访问的页面
        //"/"代表的是首页;"login"登录接口;"welcome"欢迎页;
        //"403"没有权限访问;"下面是一些静态资源"
        registry.addInterceptor(qnWebInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/login",
                        "/**/order/add");
    }
}
