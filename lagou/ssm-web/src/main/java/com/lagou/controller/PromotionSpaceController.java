package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*所有广告位查看
    * */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        //调用方法查看所有
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"查看广告ov",allPromotionSpace);

         return responseResult;
    }

/*添加 广告位方法
* */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId()==null) {
            //调用方法完成添加
            promotionSpaceService.savePromotionSpace(promotionSpace);
            //给出响应
            ResponseResult responseResult=new ResponseResult(true,200,"添加广告ov",null);

            return responseResult;
        }else {
            //调用方法完成修改
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            //给出响应
            ResponseResult responseResult=new ResponseResult(true,200,"修改广告ov",null);
            return responseResult;
        }

    }


    //修改广告位得时候回显一下数据
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){
        PromotionSpace allPromotionAdByPage = promotionSpaceService.findPromotionSpaceById(id);
        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"回显广告ov",allPromotionAdByPage);
        return responseResult;


    }


}
