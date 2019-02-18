package com.yitian.sq.dao;

import com.yitian.sq.model.RoleMenu;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
}