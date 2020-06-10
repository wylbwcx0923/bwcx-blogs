package com.bwcx.qn.flow.card.config;

import com.alibaba.fastjson.JSON;
import com.bwcx.qn.flow.card.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 不忘初心
 * 配置项目token的拦截器
 */
@Component
public class QNWebInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IPUtil ipUtil;

    /**
     * 前置拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler) throws Exception {
        System.out.println("我被拦截了");
        return true;
//        String token = (String) redisTemplate.opsForValue().get("qn_flow_token_" + ipUtil.getIpAddress(request));
//        if (null != token && "true".equals(token)) {
//            System.out.println(token);
//            return true;
//        }
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html?charset=UTF-8");
//        response.getWriter().print(JSON.toJSONString(new QNResult(false, 40003, "Dont have permission")));
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response
            , Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request
            , HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
