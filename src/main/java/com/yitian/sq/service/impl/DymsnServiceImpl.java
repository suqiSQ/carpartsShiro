package com.yitian.sq.service.impl;

import com.yitian.sq.dao.DymsnMapper;
import com.yitian.sq.model.Dymsn;
import com.yitian.sq.service.DymsnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DymsnServiceImpl implements DymsnService{
    @Autowired
    private DymsnMapper dymsnMapper;

    @Override
    public List<Dymsn> findDymsns() {
        return dymsnMapper.findDymsns();
    }
}
