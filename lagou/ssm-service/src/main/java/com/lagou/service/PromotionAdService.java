package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {
    //分页查询所有广告
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    //广告条状态管理
    public  void updatePromotionAdStatus(Integer id,Integer status);

    //新增广告功能
    public void savePromotoionAd(PromotionAd promotionAd);

    //修改广告功能
    public  void updatePromotionAd(PromotionAd promotionAd);

    //回显广告功能
    public PromotionAd findAllPromotionAdById(Integer id);

}
