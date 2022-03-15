package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(Role role) {

        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", allRole);
        return responseResult;


    }

    @Autowired
    MenuService menuService;

    //查看所有分配菜单
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        int id = -1;
        List<Menu> menuList = menuService.findSubMenuListByPid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;

    }

       /*根据角色id查询所有菜单*/
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        //调用方法完成添加
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", menuByRoleId);
        return responseResult;

    }

    /*给角色分配菜单*/
    @RequestMapping("/RoleContextMenu")
    public  ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        //调用方法
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;



    }

    /*删除角色*/
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
          //调用方法
        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "删除成功", null);
        return responseResult;



    }






}
