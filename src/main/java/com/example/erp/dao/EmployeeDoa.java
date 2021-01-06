package com.example.erp.dao;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Employees;

import java.util.List;

public interface EmployeeDoa {

    Employees getEmployee(String email);
    List<Courses> getFacultyCourses(Integer employee_id);

}
