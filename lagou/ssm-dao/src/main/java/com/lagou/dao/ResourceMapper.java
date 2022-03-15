package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;

import java.util.List;

public interface ResourceMapper {

    /*
    * 资源分页查看*/
    public List<Resource> findAllResource(ResourseVo resourseVo);


}
