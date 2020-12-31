package cn.mvpwyl.mybatis.plus.config;

import cn.mvpwyl.mybatis.plus.service.GvUrlRoleService;
import cn.mvpwyl.mybatis.plus.utils.JwtUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wangyuliang
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    @Resource(name = "gvUrlRoleService")
    private GvUrlRoleService gvUrlRoleService;

    /**
     * 在访问之前拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setContentType(ApplicationConst.CONTENT_TYPE);
        //获取请求方式:
        String method = request.getMethod();
        //获取访问路径: 项目名+servlet
        String requestURI = request.getRequestURI();
        //获取token
        String authorization = request.getHeader(ApplicationConst.Permissions.HEADER_NAME);
        if (StringUtils.isBlank(authorization) || !authorization.startsWith(ApplicationConst.Permissions.TOKEN_PREIX)) {
            //token为空,响应无权限,不是以Bearer 开头,都是不符合要求,直接pass
            String result = responseNoPermissions();
            response.getWriter().write(result);
            return false;
        }
        //程序运行至此,说明最起码token的格式是正确的,下面开始进行解析
        try {
            Claims claims = jwtUtil.parseJWT(authorization.substring(ApplicationConst.Permissions.TOKEN_PREIX_COUNT));
            //不报错,说明解析成功了
            List<String> roles = claims.get("roles", List.class);

            //如果是超级管理员,那么直接放行
            for (String role : roles) {
                if (ApplicationConst.Permissions.SUPER_ADMIN_ID.equals(role)) {
                    return true;
                }
            }
            //不是超级管理员,判断其身份,看他是否有权限
            boolean flag = gvUrlRoleService.isHavePermissionsByRoleAndMethodAndUrl(method, requestURI, roles);
            if (flag) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = responseNoPermissions();
        response.getWriter().write(result);
        return false;
    }

    //没有权限
    private String responseNoPermissions() {
        SugonResult result = new SugonResult(false, 40003, "无权限访问");
        return JSON.toJSONString(result);
    }

}

