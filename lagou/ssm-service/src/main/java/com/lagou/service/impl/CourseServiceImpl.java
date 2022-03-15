package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
   //创建权限交给spring
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        List<Course> courseByCondition = courseMapper.findCourseByCondition(courseVO);
        //把结果返回
        return courseByCondition;
    }
//封装课程
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
      /*  Course course=new Course();
        System.out.println(course+"-------gg--------"+courseVO);
        //BeanUtils就是把两个对象中相同的属性复制了第一个参数就是要获取的类第二个参数就是工具类
        BeanUtils.copyProperties(course,courseVO);
        //补全课程信息 data就是直接获取当前时间
        Date date=new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //调用方法保存课程
        courseMapper.saveCourse(course);
//获取新插入的数据id
        int Courseid=course.getId();


        //封装讲师信息,调用BeanUttils这个复制相同属性的api
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全课程信息，更新时间什么的，然后把课程id给到讲师对应的外键，就可以查询这个课程是哪个老师讲的了
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setCourseId(Courseid);
        //这个对应的就是是否删除
        teacher.setIsDel(0);
        //调用方法保存讲师信息
        courseMapper.saveTeacher(teacher);
*/
        //封装课程信息
        Course course = new Course();

        BeanUtils.copyProperties(course,courseVO);

        // 补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);

        // 获取新插入数据的id值
        int id = course.getId();

        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        // 补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);



    }


    //回显数据
    @Override
    public List<CourseVO> findCourseById(Integer id) {
        List<CourseVO> courseById = courseMapper.findCourseById(id);


        return courseById;
    }

    /*
    * 修改课程与老师*/
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        //封装课程信息
        Course course = new Course();
        System.out.println(course+"-------gg--------"+courseVO);
        BeanUtils.copyProperties(courseVO,course);

        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        // 更新课程信息
        courseMapper.updateCourse(course);


        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);

        // 补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        // 更新讲师信息
        courseMapper.updateTeacher(teacher);


    }


    /*修改课程状态
    * */
    @Override
    public void updateCourseStatus(Integer id, Integer status) {
        //封装传递过来的数据
        Course course=new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        //调用方法修改状态
        courseMapper.updateCourseStatus(course);

    }




}
