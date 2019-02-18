package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper extends BaseMapper<News> {

    List<News> findAll();
}