package com.lagou.service.impl;

import com.alibaba.druid.support.http.ResourceServlet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourseVo;
import com.lagou.service.ResourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourseServiceImpl implements ResourseService {
@Autowired
    ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResourse(ResourseVo resourseVo) {
        //先分页操作
        PageHelper.startPage(resourseVo.getCurrentPage(),resourseVo.getPageSize());

        List<Resource> allResource = resourceMapper.findAllResource(resourseVo);
//把查询好的数据放到分页里
        PageInfo<Resource> resourcePageInfo = new PageInfo<>(allResource);


        return resourcePageInfo;
    }
}
