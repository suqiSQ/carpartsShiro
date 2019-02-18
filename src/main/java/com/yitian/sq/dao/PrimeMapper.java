package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Prime;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrimeMapper extends BaseMapper<Prime> {
    List<Prime> findAll();
}