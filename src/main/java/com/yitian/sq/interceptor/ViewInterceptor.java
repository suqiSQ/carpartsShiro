package com.yitian.sq.interceptor;

import com.yitian.sq.model.Menu;
import com.yitian.sq.model.User;
import com.yitian.sq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       /* String uri= request.getRequestURI();
        if(uri.contains("login")){
            return true;
        }else{
            String key= "session:"+request.getRemoteAddr();
            if(!redisTemplate.hasKey(key)){
                response.sendRedirect(request.getContextPath()+"/login/toLogin.shtml");
                return false;
            }else{
                User user= (User) redisTemplate.opsForValue().get("session:"+request.getRemoteAddr());
                //根据 menuKey 判断请求权限
                List<Menu> menuList= userService.findMenuByUser(user);
                boolean check=false;
                for(Menu menu:menuList){
                    String menuKey= menu.getMenuKey();
                    if(uri.contains(menuKey)){
                        check=true;
                        break;
                    }
                }
                if(check){
                    return true;
                }else{
                    response.sendRedirect(request.getContextPath()+"/login/noauth.shtml");
                    return false;
                }
            }
        }*/
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        request.setAttribute("ctx",request.getContextPath());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
