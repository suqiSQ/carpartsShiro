package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BrandMapper extends BaseMapper<Brand> {
    List<Brand> findAll();
}