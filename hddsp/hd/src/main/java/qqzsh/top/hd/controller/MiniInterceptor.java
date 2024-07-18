package qqzsh.top.hd.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.hd.service.UserService;
import qqzsh.top.hd.utils.JSONResult;
import qqzsh.top.hd.utils.JsonUtils;
import qqzsh.top.hd.utils.RedisOperator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-31 11:34
 * @description
 */
public class MiniInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisOperator redis;
    public static final String USER_REDIS_SESSION = "user-redis-session";

    @Autowired
    private UserService userService;

    /**
     * 拦截请求，在controller调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws IOException {
        String userId = request.getHeader("headerUserId");
        String userToken = request.getHeader("headerUserToken");
        System.out.println(request.getRequestURI());
        System.out.println(userId+"----------"+userToken);

        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)) {
            String uniqueToken = redis.get(USER_REDIS_SESSION + ":" + userId);
            if (StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)) {
                System.out.println("请登录...");
                returnErrorResponse(response, new JSONResult().errorTokenMsg("请登录..."));
                return false;
            } else {
                if (!uniqueToken.equals(userToken)) {
                    System.out.println("账号被挤出...");
                    returnErrorResponse(response, new JSONResult().errorTokenMsg("账号被挤出..."));
                    return false;
                }else {
                    if (userService.findById(Integer.parseInt(userId)).getRole() == 1
                            && request.getRequestURI().contains("/admin")){
                        returnErrorResponse(response, new JSONResult().errorTokenMsg("盗用管理员权限，请登录..."));
                        return false;
                    }
                }
            }
        } else {
            System.out.println("请登录...");
            returnErrorResponse(response, new JSONResult().errorTokenMsg("请登录..."));
            return false;
        }

        /**
         * 返回 false：请求被拦截，返回
         * 返回 true ：请求OK，可以继续执行，放行
         */
        //放行的时候给此用户重置失效时间
        redis.expire(USER_REDIS_SESSION+":"+userId,15*60);
        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, JSONResult result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }

    /**
     * 请求controller之后，渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    /**
     * 请求controller之后，视图渲染之后
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }
}
