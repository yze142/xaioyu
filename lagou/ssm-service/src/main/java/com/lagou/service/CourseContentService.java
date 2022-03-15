package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    //课程内容查询
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);
//课程章节对应课程信息回显
    public List<Course> findCourseByCourseId(Integer courseid);

    //新增课程章节
    public void saveOrUpdateSection(CourseSection courseSection);
//修改课程章节
    public void updateSection(CourseSection courseSection);
    //修改课程状态
    public  void  updateSectionStatus(Integer id, Integer status);

//新增课时方法
    public void saveLesson(CourseLesson courseLesson);
    //修改课时方法
    public void   updateLesson(CourseLesson courseLesson);




}
