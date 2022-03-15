package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

//广告位管理
public interface PromotionSpaceService {

    //查看所有广告位
    public List<PromotionSpace> findAllPromotionSpace();

    //新增广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);
  //修改广告位
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位
    public PromotionSpace findPromotionSpaceById(Integer id);

}
