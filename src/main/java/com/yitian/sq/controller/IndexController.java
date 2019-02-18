package com.yitian.sq.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yitian.sq.model.*;
import com.yitian.sq.service.DymsnService;
import com.yitian.sq.service.NewsService;
import com.yitian.sq.service.NoticeService;
import com.yitian.sq.service.UserService;
import com.yitian.sq.util.SHA1Util;
import com.yitian.sq.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private DymsnService dymsnService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 去首页
     * @return
     */
    @RequestMapping("/index.shtml")
    public String index(){
        return "/index/index";
    }

    /**
     * 首页头信息
     * @param request
     * @return
     */
    @RequestMapping("/top.shtml")
    public String topView(HttpServletRequest request){
        Session session= SecurityUtils.getSubject().getSession();
        User user= (User)session.getAttribute("user");
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());
        request.setAttribute("date",now);
        request.setAttribute("user",user);
        return "/common/top";
    }

    /**
     * 导航栏
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/navigation.shtml")
    public String navigationView(HttpServletRequest request){
        Session session = SecurityUtils.getSubject().getSession();
        User user= (User)session.getAttribute("user");
        List<Menu> menuList= userService.findMenuByUser(user);
        request.setAttribute("menuList",menuList);
        return "/common/navigation";
    }

    /**
     * 动态消息
     */
    @RequestMapping("/dymsn.shtml")
    public String dymsnView(HttpServletRequest request){
        List<Dymsn> dymsnList= dymsnService.findDymsns();
        request.setAttribute("list",dymsnList);
        return "/index/dymsn";
    }
    /**
     * 公告分页
     */
    @RequestMapping("/notice.shtml")
    public String notice(Integer pageSize,Integer pageNo,HttpServletRequest request){
        pageNo=(pageNo==null?1:pageNo);
        pageSize=(pageSize==null?10:pageSize);
        PageHelper.startPage(pageNo,pageSize);
        List<Notice> noticeList= noticeService.findAll();
        PageInfo<Notice> page=new PageInfo<Notice>(noticeList);
        request.setAttribute("page",page);
        return "/index/notice";
    }
    /**
     * 个人设置页面
     */
    @RequestMapping("/toChangePassword.shtml")
    public String toChangePassword(){
        return "/index/changePassword";
    }
    /**
     * 修改密码
     */
    @RequestMapping("/changePassword.shtml")
    public void changePassword(String oldPwd, String newPwd, HttpServletResponse response,HttpServletRequest request) throws IOException {
        Session session= SecurityUtils.getSubject().getSession();
        User user= (User)session.getAttribute("user");
        String password= SHA1Util.encode(oldPwd);
        if(password.equals(user.getPassword())){
            UserVo userVo= new UserVo();
            userVo.setPassword(password);
            userVo.setLoginName(user.getLoginName());
            User user1= userService.findUserByLoginNameAndPassword(userVo);
            if(user1==null){
                response.getWriter().write("0");
            }else{
                user.setPassword(SHA1Util.encode(newPwd));
                userService.updateUserPassword(user);
                response.getWriter().write("1");
            }
        }else{
            response.getWriter().write("0");
        }
    }
    /**
     * 新闻页
     */
    @RequestMapping("/news.shtml")
    public String news(Integer pageNo,Integer pageSize,HttpServletRequest request){
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?10:pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<News> newsList= newsService.findAll();
        PageInfo page = new PageInfo(newsList);
        request.setAttribute("page2",page);
        return "/index/news";
    }

}
