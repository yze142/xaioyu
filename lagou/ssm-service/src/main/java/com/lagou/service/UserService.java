package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    /*修改用户状态*/
    public  void updateUserStatus(Integer id, String status);

    //分页多条件查询用户
    public PageInfo<User> findAllUser(UserVo userVo);

    /*用户登录根据传入进来的用户名判断对应的md5密码*/
   public User UserLogin(User user) throws Exception;

   /*添加用户的方法*/
 public void  addUser() throws Exception;


/*回显用户关联的角色信息方法*/
 public List<Role> findUserRelationRoleById(Integer id);

 /*用户分配角色*/
    public void userContextRole(UserVo userVo);

    /** 获取用户权限 * */
  public ResponseResult getUserPermissions(Integer id);


}
