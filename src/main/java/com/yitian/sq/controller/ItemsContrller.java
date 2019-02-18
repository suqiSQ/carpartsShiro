package com.yitian.sq.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yitian.sq.model.Brand;
import com.yitian.sq.model.Items;
import com.yitian.sq.model.Parts;
import com.yitian.sq.service.BrandService;
import com.yitian.sq.service.ItemsService;
import com.yitian.sq.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsContrller {
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private PartsService partsService;
    /**
     * 公共货架，展示，搜索，分页
     */
    @RequestMapping("/publicList.shtml")
    public String publicList(Items items, Integer pageNo, Integer pageSize, HttpServletRequest request,String brandName){
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?8:pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<Items> itemsList = itemsService.findAll(items);
        PageInfo<Items> page = new PageInfo<Items>(itemsList);//商品分页
        List<Brand> brandList= brandService.findAll();
        List<Parts> partsList= partsService.findAll();

        request.setAttribute("brandList",brandList);//品牌
        request.setAttribute("partsList",partsList);//配件
        request.setAttribute("page",page);    //商品 items
        request.setAttribute("items",items);  //模糊查询回填
        request.setAttribute("brandName",brandName);//品牌回填
        return "/items/publicList";
    }
}
