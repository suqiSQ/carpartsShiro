package com.yitian.sq.servlet;

import com.yitian.sq.util.ImageUtil;
import com.yitian.sq.webService.UserWebService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//@WebServlet("/login/Kaptcha.jpg")//注解形式，也可以web.xml配置servlet标签
public class Kaptcha extends HttpServlet {
    private static RedisTemplate redisTemplate;
    private static UserWebService userWebService;

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        Kaptcha.redisTemplate = redisTemplate;
    }
    public static void setUserWebService(UserWebService userWebService) {
        Kaptcha.userWebService = userWebService;
    }
    //发布 webService Soap ,使用 init 方法 doPost 方法会失效。
   /* @Override
    public void init() throws ServletException {
        String url="http://192.168.158.1:808/carpartsShiro/soap/webService?wsdl";
        Endpoint.publish(url,userWebService);
    }*/


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //发布 webService Soap
        String url="http://192.168.158.1:808/carpartsShiro/soap/webService?wsdl";
        Endpoint.publish(url,userWebService);


        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字符串
        String verifyCode = ImageUtil.generateVerifyCode(4);
       /* String key= "verifyCode:"+request.getRemoteAddr();
        redisTemplate.opsForValue().set(key,verifyCode.toUpperCase(),60*5, TimeUnit.SECONDS);//存入，并设置生存时间（秒)*/
        Session session= SecurityUtils.getSubject().getSession();
        session.setAttribute("validate",verifyCode.toUpperCase());
        //生成图片
        int w=200,h=80;
        //以输出流形式
        ImageUtil.outputImage(w,h,response.getOutputStream(),verifyCode);
    }


}
