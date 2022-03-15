package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    //查看所有广告位方法
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();

        return allPromotionSpace;
    }

    /*
    * 添加广告位方法*/
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //补全信息
        Date date=new Date();
        promotionSpace.setCreateTime(date);
     promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
     //给spaceKey生成随机数，
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        //调用方法完成添加
        promotionSpaceMapper.savePromotionSpace(promotionSpace);


    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //补全信息
        Date date=new Date();
        promotionSpace.setUpdateTime(date);
        //调用方法完成修改
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);


    }


    /*
    * 回显广告位方法*/
    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);


        return promotionSpaceById;
    }
}
