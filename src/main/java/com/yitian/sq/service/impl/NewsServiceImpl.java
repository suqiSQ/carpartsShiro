package com.yitian.sq.service.impl;

import com.yitian.sq.dao.NewsMapper;
import com.yitian.sq.model.News;
import com.yitian.sq.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<News> findAll() {
        return newsMapper.findAll();
    }
}
