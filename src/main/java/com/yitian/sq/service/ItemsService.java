package com.yitian.sq.service;

import com.yitian.sq.model.Items;

import java.util.List;

public interface ItemsService {
    List<Items> findAll(Items items);
}
