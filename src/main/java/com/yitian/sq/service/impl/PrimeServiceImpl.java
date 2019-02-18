package com.yitian.sq.service.impl;

import com.yitian.sq.dao.PrimeMapper;
import com.yitian.sq.model.Prime;
import com.yitian.sq.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrimeServiceImpl implements PrimeService {
    @Autowired
    private PrimeMapper primeMapper;
    @Override
    public List<Prime> findAll() {
        return primeMapper.findAll();
    }
}
