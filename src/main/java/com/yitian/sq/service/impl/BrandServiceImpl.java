package com.yitian.sq.service.impl;

import com.yitian.sq.dao.BrandMapper;
import com.yitian.sq.model.Brand;
import com.yitian.sq.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
