package com.yitian.sq.service;

import com.yitian.sq.model.Parts;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PartsService {
    public List<Parts> findAll();
}
