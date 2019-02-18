package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsMapper extends BaseMapper<Items> {

    List<Items> findAll(Items items);
}