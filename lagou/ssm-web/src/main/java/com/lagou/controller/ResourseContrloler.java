package com.lagou.controller;

import com.alibaba.druid.support.http.ResourceServlet;
import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourseContrloler {

    @Autowired
    ResourseService resourceServlet;

/*分页获取资源信息*/
    @RequestMapping("/findAllResource")
public ResponseResult findAllResource(@RequestBody ResourseVo resourseVo){

        PageInfo<Resource> allResourse = resourceServlet.findAllResourse(resourseVo);

        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"查看所有资源分页信息ov",allResourse);
        return responseResult;


    }







}
