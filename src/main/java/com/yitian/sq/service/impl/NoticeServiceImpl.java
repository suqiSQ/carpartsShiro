package com.yitian.sq.service.impl;

import com.yitian.sq.dao.NoticeMapper;
import com.yitian.sq.model.Notice;
import com.yitian.sq.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public List<Notice> findAll() {
        return noticeMapper.findAll();
    }
}
