package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.util.List;

//课程表
public interface CourseService {

    /*多条件查询课程表*/
    public List<Course> findCourseByCondition(CourseVO courseVO);

//新增课程
    public void  saveCourseOrTeacher(CourseVO  courseVO);

//回显课程信息
    public List<CourseVO> findCourseById(Integer id);

    //修改课程
public void updateCourseOrTeacher(CourseVO courseVO);

//课程状态管理
    public void updateCourseStatus(Integer id,Integer status);

}
