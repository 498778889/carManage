package com.ncu.car_manage.interceptor;

import com.github.pagehelper.util.StringUtil;
import com.ncu.car_manage.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedisSessionInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        String redisKey = getCookie(request,"redisKey");
        if (StringUtil.isNotEmpty(redisKey)) {
            try {
                //验证当前请求的session是否是已登录的session
                String userName = redisTemplate.opsForValue().get(redisKey);
                System.out.println(userName);
                if (StringUtil.isNotEmpty(userName)) {
                   return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response401(response);
        return false;
    }

    /**
     * 获取cookie
     * @param request
     * @param key
     * @return
     */
    public static String getCookie(HttpServletRequest request, String key){
        if(request == null || StringUtil.isEmpty(key)){
            return "";
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return "";
    }

    private void response401(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            //response.getWriter().print("<script>alert("+JsonResult.ERROR("用户未登录！")+")</script>");
            response.sendRedirect("/carManage/logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
