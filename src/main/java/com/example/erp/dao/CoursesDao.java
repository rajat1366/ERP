package com.example.erp.dao;

import com.example.erp.bean.Courses;

public interface CoursesDao {
    Courses getCourseByCourseCode(String course_code);
    boolean saveCourse(Courses course);
    Courses getCourseById(Integer course_id);
}
