package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import com.lagou.utilf.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/*
* 广告条类*/
@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*查询所有广告条*/
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage( PromotionAdVO promotionAdVO){
        //调用方法执行查询
        PageInfo allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"查询所有广告ov",allPromotionAdByPage);
        return responseResult;

    }

    /*上传文件*/
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
//判断接收到的文件是否为空
        if(file.isEmpty()){
            //文件为空的话直接抛出一个异常
            throw  new RuntimeException();
        }

//上传成功响应一个数据
        return new UploadUtils().fileUpload(file,request);

    }


 /*修改广告状态
 * */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        //调用方法完成修改
        promotionAdService.updatePromotionAdStatus(id,status);
        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"修改广告状态ov",null);
        return responseResult;




    }

    /*添加广告与添加广告一体化*/
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId()==null) {
//调用方法完成添加
            promotionAdService.savePromotoionAd(promotionAd);

            //给出响应
            ResponseResult responseResult=new ResponseResult(true,200,"添加广告ov",null);
            return responseResult;
        }else {

            promotionAdService.updatePromotionAd(promotionAd);
            //给出响应
            ResponseResult responseResult=new ResponseResult(true,200,"修改广告ov",null);
            return responseResult;
        }


    }
    /*回显广告*/
    @RequestMapping("/findPromotionAdById")
   public ResponseResult findPromotionAdById(Integer id){
        //调用方法
        PromotionAd allPromotionAdById = promotionAdService.findAllPromotionAdById(id);
        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"回显广告ov",allPromotionAdById);
        return responseResult;

    }




}
