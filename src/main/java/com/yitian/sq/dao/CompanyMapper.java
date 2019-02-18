package com.yitian.sq.dao;

import com.yitian.sq.base.BaseMapper;
import com.yitian.sq.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMapper extends BaseMapper<Company> {

    Company findCompanyByCompanyName(String companyName);
}