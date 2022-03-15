package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;
import java.util.Map;

//用户
public interface UserMapper {

    //多条件查询用户
    public List<User> findAllUser(UserVo userVo);

    //用户登录（根据传入进来的手机号找）
    public User UserLogin(User user);

    //新增用户
    public void addUser(User user);



    /*根据用户ID清空中间表 */
    void deleteUserContextRole(Integer userId);

    /* 分配角色 */

    void userContextRole(User_Role_relation user_role_relation);

    //1.根据用户id查询关联的角色信息
    public List<Role> findUserRelationRoleById(Integer id);

    //2.根据角色id查询父菜单
    public List<Menu>  findParentMenuByRoleId(List<Integer> roleid);

    //3.再对父菜单关联的子菜单进行关联查询
   public List<Menu> findSubMenuByPid(int pid);

    //4.根据角色id获取资源信息
   public List<Resource>   findResourceByRoleId(List<Integer> ids);
    
     public List<Resource>   afindResourceByRoleId(List<Integer> ids);


}
