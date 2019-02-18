package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.User;
import com.yitian.sq.vo.UserVo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    User findUserByLoginNameAndPassword(UserVo vo);

    User findUserByLoginName(String loginName);

    User findUserByPhone(String phone);

    User findUserByEmail(String email);

    User findUserByLoginNameAndEmail(UserVo vo);

    int updateByPrimaryKeySelective(User user);

}