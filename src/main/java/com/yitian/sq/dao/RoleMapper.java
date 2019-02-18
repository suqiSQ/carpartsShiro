package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Role;
import com.yitian.sq.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRoleByUser(User user);
}