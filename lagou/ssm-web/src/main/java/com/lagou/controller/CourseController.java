package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//@ResponseBody＋Contriller的组合注解
@RequestMapping("/course")
public class CourseController {

    @Autowired
   private CourseService courseService;

    //按条件查询课程的方法 这里的参数要用到一个RequesBody的注解用来注解这个参数
    @RequestMapping("/findAllCourse")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        //调用service
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        //这时候不能直接返回一个COurse的实体类回去，现在要返回一个响应数据回去
        //就要调用ResponseResult这个响应实体类了 true就是成功 200就是状态吗 obje就是响应的数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",courseList);
        //能走到这一步就是成功了，直接返回数据即可
               return result;
    }


@RequestMapping("/test")
    public String test(){

        return "test";
}


/*上传文件*/
@RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
//判断接收到的文件是否为空
    if(file.isEmpty()){
        //文件为空的话直接抛出一个异常
        throw  new RuntimeException();
    }
//获取项目部署路径 / apache-tomcat-8.5.56 webapps ssm_web
    String realPath=request.getServletContext().getRealPath("/");//获取路径，截取字符串
    String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
//获取源文件名字
    String originalFilename = file.getOriginalFilename();
    //生成新文件名字
    //currentTimeMillis根据当前时间生成一个新名字 后面的substring就是截取文件名字 。后面的文件后缀
    String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

    //最后就是文件上传，首先给要指定的路径apache-tomcat-8.5.56 webapps upload
String uploadPath=substring+"upload\\";
    File filePath=new File(uploadPath,newFileName);
    //如果不存在就生成一个目录
    if(!filePath.getParentFile().exists()){
        filePath.getParentFile().mkdirs();
        System.out.println("创建目录:"+filePath);
    }
/*//图片实现真正的上传
*
* */
    file.transferTo(filePath);
    //将文件名和文件路径返回，进行响应
    HashMap<String, String> map = new HashMap<>();
    map.put("FileName",newFileName);
    //文件路径和文件名
    map.put("FilePath"," http://localhost:8080/upload/"+newFileName);
    ResponseResult responseResult = new ResponseResult(true, 200, "上传成功", map);
//上传成功响应一个数据
    return responseResult;
}

/*//新增课程信息or修改课程哦
*
* */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
//判断前台传递过来的数据是否为空，如果为空就调用添加方法
        if(courseVO.getId()==null){
            //如果是空就添加响应

            //调用service完成添加功能
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult=new ResponseResult(true,200,"添加功能响应成功",null);
               return responseResult;
        }else {
            //那么就是不为空就是要调用修改，纯纯的傻逼代码
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult=new ResponseResult(true,200,"修改功能响应成功",null);
            return responseResult;

        }




    }

/*回显数据
*
* */
  @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(  Integer id){
      List<CourseVO> courseById = courseService.findCourseById(id);

      ResponseResult responseResult=new ResponseResult(true,200,"回显课程成功",courseById);

 return responseResult;
  }

/*课程状态管理*/
@RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id,@RequestParam Integer status){
//调用service来修改状态
    courseService.updateCourseStatus(id, status);
    //响应回去
                Map<String,Object> map=new HashMap();
                      map.put("Status",status);
    ResponseResult responseResult=new ResponseResult(true,200,"响应成功",map);
                  return responseResult;

}


}