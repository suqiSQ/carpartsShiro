package com.yitian.sq.controller;

import com.yitian.sq.model.*;
import com.yitian.sq.security.EmailRunnable;
import com.yitian.sq.security.LoginToken;
import com.yitian.sq.service.*;
import com.yitian.sq.util.SHA1Util;
import com.yitian.sq.vo.RegisterVo;
import com.yitian.sq.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private PartsService partsService;
    @Autowired
    private PrimeService primeService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    /**
     * 去登录页面
     * @return
     */
    @RequestMapping("/toLogin.shtml")
    public String toLogin(HttpServletRequest request){
       /* User user= (User) redisTemplate.opsForValue().get("session:"+request.getRemoteAddr());
        if(user!=null){
            redisTemplate.delete("session:"+request.getRemoteAddr());
            redisTemplate.delete("session:"+user.getLoginName());
        }*/
        return "/login/login";
    }

    /**
     * 验证码判断及登陆用户判断
     * @param vo
     * @param
     * @param response
     * @throws IOException
     */
    @RequestMapping("/login.shtml")
    public void login(UserVo vo, HttpServletResponse response,HttpServletRequest request) throws IOException {
        // 取到 subject 对象，shiro 创建
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌，登录认证用
        LoginToken token = new LoginToken(vo.getLoginName(),vo.getPassword(),false,vo.getValidate());
        String result="1";
        try{
            subject.login(token);
        }catch (Exception e){
            result=e.getMessage();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result);


         /*String validate= (String) redisTemplate.opsForValue().get("verifyCode:"+request.getRemoteAddr());
        if(validate.equalsIgnoreCase(vo.getValidate())){
            vo.setPassword(SHA1Util.encode(vo.getPassword()));//加密后对比
            User user= userService.findUserByLoginNameAndPassword(vo);
            if(user!=null){
                if(redisTemplate.hasKey("session:"+user.getLoginName())){
                    String address= (String) redisTemplate.opsForValue().get("session:"+user.getLoginName());
                    response.getWriter().write(address);
                }else{
                    //redisTemlate 不能存对象，需要定义序列化泛型，被存的对象需实现 serializable 接口
                    ValueOperations<Serializable, Serializable> operations = redisTemplate.opsForValue();
                    operations.set("session:"+request.getRemoteAddr(),user,60*60*24, TimeUnit.SECONDS);
                    operations.set("session:"+user.getLoginName(),request.getRemoteAddr());
                    response.getWriter().write("3");
                }
            }else{
                response.getWriter().write("2");
            }
        }else{
           response.getWriter().write("1");
        }*/

    }

    /**
     * 去注册页面
     */
    @RequestMapping("/toRegister.shtml")
    public String toRegister(HttpServletRequest request){
        List<Brand> brandList= brandService.findAll();
        List<Parts> partsList= partsService.findAll();
        List<Prime> primeList= primeService.findAll();
        request.setAttribute("brandList",brandList);
        request.setAttribute("partsList",partsList);
        request.setAttribute("primeList",primeList);
        return "/login/register";
    }
    /**
     * 用户名唯一校验
     */
    @RequestMapping("/checkLoginName.shtml")
    public void checkLoginName(String loginName,HttpServletResponse response) throws IOException {
        boolean check= userService.findUserByLoginName(loginName);
        if(check){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
    /**
     * 手机号唯一校验
     */
    @RequestMapping("/checkPhone.shtml")
    public void checkPhone(String phone,HttpServletResponse response) throws IOException {
        boolean check= userService.findUserByPhone(phone);
        if(check){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
    /**
     * 邮箱唯一校验
     */
    @RequestMapping("/checkEmail.shtml")
    public void checkEmail(String email,HttpServletResponse response) throws IOException {
        boolean check = userService.findUserByEmail(email);
        if(check){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
    /**
     * 企业名称唯一校验
     */
    @RequestMapping("/checkCompanyname.shtml")
    public void checkCompanyname(String companyname,HttpServletResponse response) throws IOException {
        boolean check= companyService.findCompanyByCompanyName(companyname);
        if(check){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
    /**
     * 加载省
     */
    @RequestMapping("getProvince.shtml")
    public @ResponseBody List<City> getProvince(){
        List<City> cityList= cityService.findProvinceByPId(100000);
        return cityList;
    }
    /**
     * 改变省
     */
    @RequestMapping("/getCity.shtml")
    public @ResponseBody List<City> getProvince(int id){
        List<City> cityList= cityService.findProvinceByPId(id);
        return cityList;
    }
    /**
     * 注册
     */
    @RequestMapping("/register.shtml")
    public String register(RegisterVo vo){
        userService.insertUserAndCompany(vo);
        return "redirect:/login/toLogin.shtml";
    }
    /**
     * 忘记密码页面
     */
    @RequestMapping("/forgetPassword.shtml")
    public String getPassword(){
        return "/login/forgetPassword";
    }
    /**
     * 生成新密码，发送邮件，并修改数据库密码
     */
    @RequestMapping("/getPassword.shtml")
    public void getPassword(UserVo vo,HttpServletResponse response) throws IOException {
        User user= userService.findUserByLoginNameAndEmail(vo);
        if(user==null){
            response.getWriter().write("0");
        }else{
            //生成新密码
            String password="";
            Random random= new Random();
            for(int i=0;i<6;i++){
                password+=random.nextInt(10);
            }
            //发送邮件，依赖 java mail ,spring旗下 mailSender  support
            // 客户端需开通 smtp 协议
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setSubject("欢迎使用密码找回功能");
            mailMessage.setText("您的新密码是："+password);
            mailMessage.setFrom("sq13473119091@sina.com");
            mailMessage.setTo(user.getEmail());
            //mailSender.send(mailMessage);

            //修改数据库的密码
            String passwords= SHA1Util.encode(password);  //加密
            user.setPassword(passwords);
            userService.updateUserPassword(user);
            response.getWriter().write("1");

            Runnable emailRunnable = new EmailRunnable(mailMessage); //线程类
            threadPoolTaskExecutor.execute(emailRunnable);              //线程池
        }
    }
    /**
     * 非法页
     */
    @RequestMapping("/noauth.shtml")
    public String noauth(){
        return "/common/noauth";
    }
}
