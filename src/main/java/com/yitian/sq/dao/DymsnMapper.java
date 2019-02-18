package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Dymsn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DymsnMapper extends BaseMapper<Dymsn> {
    List<Dymsn> findDymsns();

}