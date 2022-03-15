package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*查询所有菜单*/
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> allMenu = menuService.findAllMenu();

        ResponseResult responseResult=new ResponseResult(true,200,"查看所有菜单",allMenu);

        return responseResult;


    }

    //回显菜单
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //判断id值是更新还是添加操作
        if(id==-1){
            //添加 回显信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            //封装数据
            Map<String, Object> map=new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);


            ResponseResult responseResult=new ResponseResult(true,200,"添加所有菜单",map);

            return responseResult;
        }else {

            //义眼丁真鉴定为-修改
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            List<Menu> menuByid = menuService.findMenuByid(id);
            //封装数据
            Map<String, Object> map=new HashMap<>();
            map.put("menuInfo",menuByid);
            map.put("parentMenuList",subMenuListByPid);


            ResponseResult responseResult=new ResponseResult(true,200,"修改回显所有菜单",map);

            return responseResult;
        }


    }


}
