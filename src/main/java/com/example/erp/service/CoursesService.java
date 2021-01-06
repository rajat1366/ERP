package com.example.erp.service;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.bean.Students_Courses;
import com.example.erp.dao.implementation.CoursesDaoImpl;

import java.util.List;

public class CoursesService {
    CoursesDaoImpl coursesDao = new CoursesDaoImpl();

    public Courses getCourseByCourseCode(String course_code) {
        return coursesDao.getCourseByCourseCode(course_code);
    }

    public boolean registerTA(Integer course_id, Integer roll_no) {
        return coursesDao.registerTA(course_id,roll_no);
    }

    public Courses getCourseById(Integer course_id) {
       return coursesDao.getCourseById(course_id);
    }

    public boolean detTA(Integer course_id, Integer studentId) {
        return coursesDao.delTA(course_id,studentId);
    }

    public List<Students_Courses> getCourseStudents(Integer course_id) {
        return coursesDao.getcourseStudents(course_id);
    }

    public List<Students> getCourseTAList(Integer course_id) {
         try {
             return coursesDao.getCourseTAList(course_id);
         } catch (Exception e ){
             e.printStackTrace();
             return null;
        }
    }

    public boolean checkStudentInCourseTAList(Integer course_id, Integer studentId) {
        return coursesDao.checkStudentInCourseTAList(course_id,studentId);
    }
}
