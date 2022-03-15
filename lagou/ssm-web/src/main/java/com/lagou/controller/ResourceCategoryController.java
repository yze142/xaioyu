package com.lagou.controller;

import com.alibaba.druid.support.http.ResourceServlet;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategor(){

        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"分类查询广告ov",allResourceCategory);
        return responseResult;


    }





}
