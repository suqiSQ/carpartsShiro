package com.yitian.sq.webService;


import com.yitian.sq.model.User;
import com.yitian.sq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class RestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/{loginName}.shtml")
    public @ResponseBody User findUserByLoginName(@PathVariable("loginName") String loginName){
        User user= userService.findUserByName(loginName);
        return user;
    }
}
