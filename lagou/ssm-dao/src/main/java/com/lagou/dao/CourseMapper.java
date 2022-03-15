package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /*多条件课程列表查询*/

    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*** 保存课程信息 */
    public int saveCourse(Course course);

    /*** 保存讲师信息 * */
    public void saveTeacher(Teacher teacher);


    /*回显课程信息，根据id查询到*/
    public List <CourseVO> findCourseById(Integer id);

    /*修改Course*/
    public void updateCourse(Course course);
    /*修改Teacher*/
    public void updateTeacher(Teacher teacher);

    /*课程状态修改*/
public void updateCourseStatus(Course course);


}
