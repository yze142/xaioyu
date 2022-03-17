package com.lagou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImol implements UserService {
    @Autowired
    private UserMapper userMapper;

   /*修改用户状态*/
    @Override
    public void updateUserStatus(Integer id, String status) {
         User user=new User();
         user.setId(id);
         user.setStatus(status);

        userMapper.updateUserStatus(user);
    }

    //分页多条件查询用户
    @Override
    public PageInfo<User> findAllUser(UserVo userVo) {
        //调用插件来晚餐插件查询
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        //调用方法完成查询
        List<User> allUser = userMapper.findAllUser(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUser);

        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("每页显示长度：" + pageInfo.getPageSize());
        System.out.println("是否第一页：" + pageInfo.isIsFirstPage());
        System.out.println("是否最后一页：" + pageInfo.isIsLastPage());


        return pageInfo;
    }


    //用户登录
    @Override
    public User UserLogin(User user) throws Exception {

        User user2 = userMapper.UserLogin(user);
        //判断用户是否存在 思路就是判断用户传入进来的用户是否存在，如果用户存在，那么获取到他查询出来
        //的密码然后跟用户输入进来的密码经过md5加密之后再次进行对比判断是否正确
        if (user2 != null && Md5.verify(user.getPassword(), "lagou", user2.getPassword())) {

            return user2;
        } else {
            return null;
        }


    }

    /*添加用户的方法*/
    @Override
    public void addUser() throws Exception {
        User user = new User();


        user.setPassword("142142");

        String lagou = Md5.md5(user.getPassword(), "lagou");
        User user2 = new User();
        user2.setId(100030023);
        user2.setName("yze142");
        user2.setPhone("13215185319");
        user2.setPassword(lagou);


        userMapper.addUser(user2);

    }

    /*回显用户对应的角色信息*/
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {

        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);

        return userRelationRoleById;
    }

    /*分配用户角色
     * */
    @Override
    public void userContextRole(UserVo userVo) {
        //先调用删除方法清理一下用户和角色表的中间关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        //然后遍历传入进来的角色id添加进去
        for (Integer role_id : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            //传入进来分配好的角色Id
            user_role_relation.setRoleId(role_id);
            //用户id
            user_role_relation.setUserId(userVo.getUserId());
            //更新时间与创建时间
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            //创建人与修改名
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }


    }

    //获取用户权限

    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //1.获取当前用户拥有的角色
        List<Role> resourceList = userMapper.findUserRelationRoleById(userid);

        //2.获取角色id保存到list中
        ArrayList<Integer> roleid = new ArrayList<>();

        for (Role role : resourceList) {
            roleid.add(role.getId());
        }

        //3.根据角色id查询菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleid);

        //4.根据父菜单查询到子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());

            //需要把查询出来的紫菜单存到集合里
            menu.setSubMenuList(subMenuByPid);
        }

        //5.最后就是查询到资源
        List<Resource> resourceByRoleId = userMapper.findResourceByRoleId(roleid);

//6.封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);

        //menuList: 菜单权限数据
        map.put("resourceList",resourceList);

        //resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
             return result;

    }
    }
