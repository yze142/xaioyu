package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/*资源分页查询之种类查询*/
public interface ResourceCategoryMapper {

    /*资源分页查询之种类查询*/
    public List <ResourceCategory>  findAllResourceCategory( );


}
