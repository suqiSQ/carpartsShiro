package com.yitian.sq.service.impl;

import com.yitian.sq.dao.CityMapper;
import com.yitian.sq.model.City;
import com.yitian.sq.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Override
    public List<City> findProvinceByPId(int id) {
        return cityMapper.findProvinceByPId(id);
    }
}
