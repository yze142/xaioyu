package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    //课程内容类
public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

/*课程章节对应的课程信息回显
* */
public List<Course> findCourseByCourseId(Integer courseId);

/*新增章节信息
* */
public void  saveSection(CourseSection courseSection);
/*修改章节
* updateSectionStatus*/
public void updateSection(CourseSection courseSection);

/*新增课程方法
* */
public void saveLesson(CourseLesson courseLesson);
//修改课时方法
    public void updateLesson(CourseLesson courseLesson);

    /*修改章节状态方法*/
    public void  updateSectionStatus(CourseSection courseSection);

}
