package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    //查询所有父子菜单
    public List<Menu> findSubMenuListByPid(int pid);

    //查看所有菜单
    public List<Menu> findAllMenu();

//鉴定为修改操作，进行回显
   public List<Menu> findMenuByid(Integer id);
}
