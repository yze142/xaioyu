package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContenServiceImol implements CourseContentService {

    @Autowired
    CourseContentMapper courseContentMapper;
    /*课程内容查询*/
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id) {
        List<CourseSection> sectionAndLessonByCourseId = courseContentMapper.findSectionAndLessonByCourseId(id);


        return sectionAndLessonByCourseId;
    }

    /*回显课程章节对应的课程信息*/
    @Override
    public List<Course> findCourseByCourseId(Integer courseid) {
        List<Course> course = courseContentMapper.findCourseByCourseId(courseid);

        return course;
    }

    /*新增章节   章节外键id是当用户点到课程然后点击添加的时候就发送过来了，所以不需要我们来添加*/
    @Override
    public void saveOrUpdateSection(CourseSection courseSection) {
        //补全信息
        Date date=new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //调用方法新增
        courseContentMapper.saveSection(courseSection);


    }


    /*修改课程章节*/
    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        Date date=new Date();
        courseSection.setUpdateTime(date);

        //调用信息

        courseContentMapper.updateSection(courseSection);

    }


    /*
    * 修改课程章节状态*/

    @Override
    public void updateSectionStatus(Integer id ,Integer status) {
        //封装信息
        CourseSection courseSection=new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        //修改状态
        courseContentMapper.updateSectionStatus(courseSection);



    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        Date date=new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        //调用方法
        courseContentMapper.saveLesson(courseLesson);

    }

    //修改课时方法
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        //补全信息
        Date date=new Date();
        courseLesson.setUpdateTime(date);
        //调用方法
        courseContentMapper.updateLesson(courseLesson);

    }


}
