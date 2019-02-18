package com.yitian.sq.service.impl;

import com.yitian.sq.dao.ItemsMapper;
import com.yitian.sq.model.Items;
import com.yitian.sq.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> findAll(Items items) {
        return itemsMapper.findAll(items);
    }
}
