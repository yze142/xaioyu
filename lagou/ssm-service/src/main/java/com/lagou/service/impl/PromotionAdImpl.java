package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PromotionAdImpl implements PromotionAdService{
    @Autowired
    PromotionAdMapper promotionAdMapper;


    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
//调用分页插件进行分页，然后查询出来进行展示
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());

//查询所有的广告条数并且分页处理，而且回显了广告位名称
        List<PromotionAd> allAdByPage = promotionAdMapper.findAllAdByPage();

        PageInfo<PromotionAd> promotionAdPageInfo = new PageInfo<>(allAdByPage);

        return promotionAdPageInfo;
    }

//修改广告条状态
    @Override
    public void updatePromotionAdStatus(Integer id, Integer status) {
        //把id和状态值封装进去
        PromotionAd promotionAd=new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
       //调用方法完成修改
        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }

    //添加广告功能

    @Override
    public void savePromotoionAd(PromotionAd promotionAd) {
        //补全信息
        Date date=new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        //完成添加
        promotionAdMapper.savePromotionAd(promotionAd);


    }
//修改广告的方法
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        //补全信息
        Date date=new Date();
        promotionAd.setUpdateTime(date);
        //完成修改
        promotionAdMapper.updatePromotionAd(promotionAd);


    }

    @Override
    public PromotionAd findAllPromotionAdById(Integer id) {
        PromotionAd promotionAdById = promotionAdMapper.findPromotionAdById(id);

        return promotionAdById;
    }
}
