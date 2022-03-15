package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

@Autowired
private CourseContentService courseContentService;

/*查询课程的详细内容，比如章节和课时什么的*/
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId){
        //调用方法查询课程
        List<CourseSection> sectionAndLessonByCourseId = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult=new ResponseResult(true,200,"查询成功",sectionAndLessonByCourseId);

        return responseResult;
    }

    /* 回显课程章节对应的课程信息*/
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        //调用方法回显对应的课程信息
        List<Course> courseByCourseId = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult= new ResponseResult(true,200,"响应成功",courseByCourseId);

                     return responseResult;
    }

/*
* 新增课程和修改课程章节一体方法
* */
      @RequestMapping("/saveOrUpdateSection")
 public  ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

       //判断是否id为空，如果为空就是添加方法
          if (courseSection.getId()==null) {
              //调用方法添加课程
              courseContentService.saveOrUpdateSection(courseSection);

              //给出响应
              ResponseResult responseResult= new ResponseResult(true,200,"添加章节成功",null);

              return responseResult;
          }else {
              //id是存在的所以调用方法进行修改
              courseContentService.updateSection(courseSection);
              //给出响应
   ResponseResult responseResult= new ResponseResult(true,200,"修改课程章节成功",null);

              return responseResult;
          }
      }


      /*
      * 修改课程章节状态
      * */
    @RequestMapping("/updateSectionStatus")
      public ResponseResult updateSectionStatus(Integer id,Integer status){

        //调用方法修改章节状态
        courseContentService.updateSectionStatus(id,status);
        //给出响应
        ResponseResult responseResult= new ResponseResult(true,200,"成功修改了课程状态",null);

        return responseResult;

    }




/*
* 新增课时与修改课时
* */
     @RequestMapping("/UpdateOrsaveLesson")
    public ResponseResult UpdateOrsaveLesson(@RequestBody CourseLesson courseLesson){
         if (courseLesson.getId()==null) {
             //添加课时
             courseContentService.saveLesson(courseLesson);
             //给出响应
             ResponseResult responseResult= new ResponseResult(true,200,"添加课时成功",null);

             return responseResult;
         }else {
             //不是null就修改呗
             courseContentService.updateLesson(courseLesson);
             //给出响应
      ResponseResult responseResult= new ResponseResult(true,200,"修改课时成功",null);

             return responseResult;
         }

     }


}
