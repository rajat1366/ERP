package com.example.erp.dao;

import com.example.erp.bean.Students;

public interface StudentDao {

    Students getStudent(String email);
    Students getStudentByRollNo(String roll_no);
    void saveStudent(Students student);

}
