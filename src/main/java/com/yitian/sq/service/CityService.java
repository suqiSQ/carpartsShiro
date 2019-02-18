package com.yitian.sq.service;

import com.yitian.sq.model.City;

import java.util.List;

public interface CityService {
    public List<City> findProvinceByPId(int id);
}
