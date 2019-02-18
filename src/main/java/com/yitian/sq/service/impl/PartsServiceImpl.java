package com.yitian.sq.service.impl;

import com.yitian.sq.dao.PartsMapper;
import com.yitian.sq.model.Parts;
import com.yitian.sq.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartsServiceImpl implements PartsService {
    @Autowired
    private PartsMapper partsMapper;
    @Override
    public List<Parts> findAll() {
        return partsMapper.findAll();
    }
}
