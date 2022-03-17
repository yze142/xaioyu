package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        //查询
        PageInfo<User> allUser = userService.findAllUser(userVo);
        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"分页查询用户ov",allUser);
        return responseResult;


    }

    /*
    用户登录*/
 @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
     //判断用户密码是否正确
     User user1 = userService.UserLogin(user);
     if(user1!=null){
         //保存用户id级access_token到session中
         HttpSession session=request.getSession();
         String access_token= UUID.randomUUID().toString();
         session.setAttribute("access_token",access_token);
         session.setAttribute("user_id",user1.getId());

         //将查询出来的值响应给前台
         Map<String, Object> map=new HashMap();
         map.put("access_token",access_token);
         map.put("user_id",user1.getId());
//给出响应
         ResponseResult responseResult=new ResponseResult(true,200,"登录成功",map);
         return responseResult;


     }else {


         //用户名或者密码错误
         //给出响应
         ResponseResult responseResult=new ResponseResult(false,400,"用户名或者密码错误捏",null);
         return responseResult;

     }

 }

 /*
 * 悄咪咪添加用户*/
    @RequestMapping("/addUser")
   public ResponseResult addUser() throws Exception {
       userService.addUser();
        //给出响应
        ResponseResult responseResult=new ResponseResult(true,200,"添加成功",null);
        return responseResult;

   }

   /*回显用户对应的角色信息*/
   @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){

       List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
       //给出响应
       ResponseResult responseResult=new ResponseResult(true,200,"回显用户对应的角色",userRelationRoleById);
       return responseResult;

   }

   /*给用户分配角色*/
   @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
       //调用方法完成添加
       userService.userContextRole(userVo);
       //给出响应
       ResponseResult responseResult=new ResponseResult(true,200,"给用户分配角色",null);
       return responseResult;



   }

    /*
        获取用户权限，进行菜单动态展示
     */

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        // 2.获取session中token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3.判断token是否一致
        if(header_token.equals(session_token)){
            // 获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            // 调用service,进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
//
        }


    }

    /*修改用户状态*/
    @RequestMapping("/updateUserStatus")
    public  ResponseResult updateUserStatus(Integer id,String status){
        //调用方法
         userService.updateUserStatus(id,status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
        return responseResult;


    }

}
