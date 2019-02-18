package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Menu;
import com.yitian.sq.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> findMenuByUser(User user);

}