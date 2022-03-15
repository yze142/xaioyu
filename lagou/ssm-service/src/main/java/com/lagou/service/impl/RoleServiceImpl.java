package com.lagou.service.impl;

import com.lagou.dao.RoleMapperA;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapperA roleMapperA;


    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapperA.findAllRole(role);

        return allRole;
    }

    /*根据角色id查询菜单信息*/
    @Override
    public List<Integer> findMenuByRoleId(Integer id) {
        List<Integer> menuByRoleId = roleMapperA.findMenuByRoleId(id);
        return menuByRoleId;
    }

    /*根据角色id分配菜单*/
    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
// 先删除关系表对应的用户，然后再分配
        roleMapperA.deletRoleContextMenu(roleMenuVo.getRoleId());
//再次分配
        for (Integer o : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation=new Role_menu_relation();
            //用户id
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            //菜单id
            role_menu_relation.setMenuId(o);

            role_menu_relation.setCreatedTime(new Date());

            role_menu_relation.setUpdatedTime(new Date());

            role_menu_relation.setCreatedBy("system");

            role_menu_relation.setUpdatedby("system");

            roleMapperA.RoleContextMenu(role_menu_relation);

        }


    }

    /*根据id删除角色*/
    @Override
    public void deleteRole(Integer id){
        //在删除之前先要删除对应的关联信息
        roleMapperA.deletRoleContextMenu(id);
        //删除角色
        roleMapperA.deleteRole(id);



    }




}
