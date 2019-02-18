package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Parts;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PartsMapper extends BaseMapper<Parts> {
    List<Parts> findAll();
}