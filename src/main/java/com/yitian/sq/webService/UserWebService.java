package com.yitian.sq.webService;

import com.yitian.sq.model.User;

public interface UserWebService {
    public User findUserByName(String loginName);
}
