package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImol implements MenuService {
    @Autowired
   private MenuMapper menuMapper;

    /*查看所有父子菜单*/
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);

        return subMenuListByPid;
    }

    /*查询所有菜单*/
    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();


        return allMenu;
    }
/*
* 鉴定为修改操作需要回显对应的数据*/
    @Override
    public List<Menu> findMenuByid(Integer id) {
        List<Menu> menuById = menuMapper.findMenuById(id);

        return menuById;

    }


}
