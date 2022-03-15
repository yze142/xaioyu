package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

import java.util.List;

public interface ResourseService {

    //分页查询所有捏资源
    public PageInfo<Resource> findAllResourse(ResourseVo resourseVo);


}
