package com.yitian.sq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {
    /**
     * 企业管理页
     * @return
     */
    @RequestMapping("/company.shtml")
    public String company(){
        return "/company/companyManage";
    }
}
