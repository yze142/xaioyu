package com.lagou.dao;

import com.lagou.domain.Menu;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface MenuMapper {
    //查询父子所有的菜单
    public List<Menu> findSubMenuListByPid(int pid);

  //查询所有菜单
    public List<Menu> findAllMenu();

//鉴定为修改，然后回显对应的数据
  public    List<Menu> findMenuById(Integer id);
}
