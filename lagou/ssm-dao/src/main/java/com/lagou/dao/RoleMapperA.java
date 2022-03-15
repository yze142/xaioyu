package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapperA {
    //查询所有角色、
    public List<Role> findAllRole(Role role);

    //查询角色对应的菜单信息
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*根据传递过来的角色id删除中间表的关系*/
    public void deletRoleContextMenu(Integer roleId);

    /* 角色菜单关联 */
    void RoleContextMenu(Role_menu_relation role_menu_relation);


    /*删除角色*/
    void deleteRole(Integer id);



}
