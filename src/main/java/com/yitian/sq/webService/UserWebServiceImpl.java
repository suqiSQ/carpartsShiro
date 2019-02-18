package com.yitian.sq.webService;

import com.yitian.sq.dao.UserMapper;
import com.yitian.sq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
//发布服务，注解
@WebService
public class UserWebServiceImpl implements UserWebService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByName(String loginName) {
        return userMapper.findUserByLoginName(loginName);
    }
}
