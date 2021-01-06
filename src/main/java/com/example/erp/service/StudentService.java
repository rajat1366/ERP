package com.example.erp.service;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.dao.implementation.StudentDaoImpl;

import java.util.List;

public class StudentService {
    StudentDaoImpl studentDao = new StudentDaoImpl();
    public Students getStudent(String email){
        return studentDao.getStudent(email);
    }
    public Students getStudentByRollNo(String roll_no){
        return studentDao.getStudentByRollNo(roll_no);
    }

    public Students getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    public List<Courses> getStudentTACourses(String roll_no) {
       try{
           return studentDao.getStudentTACourses(roll_no);
       } catch (Exception e){
           return null;
       }

    }

    public Integer getStudentIdFromRollNo(String roll_no) {
        return studentDao.getStudentIdFromRollNo(roll_no);
    }
}
