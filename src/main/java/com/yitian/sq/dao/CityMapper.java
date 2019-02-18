package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper extends BaseMapper<City> {

    List<City> findProvinceByPId(int id);
}