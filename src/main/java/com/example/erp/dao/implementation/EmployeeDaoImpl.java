package com.example.erp.dao.implementation;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Employees;
import com.example.erp.bean.Students;
import com.example.erp.dao.EmployeeDoa;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDoa {

    @Override
    public Employees getEmployee(String email){
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Employees where email=:email");
            query.setParameter("email", email);
            Employees emp =(Employees) query.uniqueResult();
            session.close();
            return emp;

        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Courses> getFacultyCourses(Integer employee_id){
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Courses where faculty=:employee_id");
            query.setParameter("employee_id", employee_id);
            List<Courses> list  =query.list();
            session.close();
            return list;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }



}
