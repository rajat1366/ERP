package com.example.erp.service;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Employees;
import com.example.erp.bean.Students;
import com.example.erp.dao.implementation.EmployeeDaoImpl;
import com.example.erp.dao.implementation.StudentDaoImpl;
import org.hibernate.HibernateException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeesService {

    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    StudentDaoImpl studentsDao = new StudentDaoImpl();

    public Employees getEmployee(String email) {
        return employeeDao.getEmployee(email);
    }

    public List<Courses> getEmpCourses(Integer employee_id) {
        return employeeDao.getFacultyCourses(employee_id);
    }


}
