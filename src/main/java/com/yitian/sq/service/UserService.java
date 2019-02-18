package com.yitian.sq.service;

import com.yitian.sq.model.Menu;
import com.yitian.sq.model.Role;
import com.yitian.sq.model.User;
import com.yitian.sq.vo.RegisterVo;
import com.yitian.sq.vo.UserVo;

import java.util.List;

public interface UserService {
    public User findUserByLoginNameAndPassword(UserVo vo);
    public List<Menu> findMenuByUser(User user);
    public Boolean findUserByLoginName(String loginName);

    Boolean findUserByPhone(String phone);

    boolean findUserByEmail(String email);

    void insertUserAndCompany(RegisterVo vo);

    User findUserByLoginNameAndEmail(UserVo vo);

    void updateUserPassword(User user);

    List<Role> findRoleByUser(User user);

    public User findUserByName(String loginName);
}
