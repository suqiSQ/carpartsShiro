package com.yitian.sq.service.impl;

import com.yitian.sq.dao.CompanyMapper;
import com.yitian.sq.model.Company;
import com.yitian.sq.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public boolean findCompanyByCompanyName(String companyName) {
        Company company= companyMapper.findCompanyByCompanyName(companyName);
        if(company==null){
            return true;
        }else{
            return false;
        }
    }
}
