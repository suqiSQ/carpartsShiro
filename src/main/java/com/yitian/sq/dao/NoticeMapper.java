package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> findAll();
}