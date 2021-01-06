package com.example.erp.dao.implementation;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.dao.StudentDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Students getStudent(String email) {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Students where email=:email");
            query.setParameter("email", email);
            Students s =(Students) query.uniqueResult();
            session.close();
            return s;

        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public Students getStudentByRollNo(String roll_no) {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Students where roll_number=:roll_no");
            query.setParameter("roll_no", roll_no);
            Students s =(Students) query.uniqueResult();
            return s;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveStudent(Students student)  {
        Session session = SessionUtil.getSession();
        try
        {
            Transaction transaction =  session.beginTransaction();
            session.update(student);
            transaction.commit();

        }catch (HibernateException e){
            throw new HibernateException(e);
        }
    }

    public Students getStudentById(int studentId) {
        Session session = SessionUtil.getSession();
        try {
            return session.get(Students.class,studentId);
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Courses> getStudentTACourses(String roll_no) throws Exception {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Students where roll_number=:roll_no");
            query.setParameter("roll_no", roll_no);
            Students s =(Students) query.uniqueResult();
            if(s.getCourses().size()>0){
                System.out.println(s.getCourses().get(0).getCourse_code());
            }
            List<Courses> studentTAcourses = s.getCourses();
            return studentTAcourses;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public Integer getStudentIdFromRollNo(String roll_no) {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Students where roll_number=:roll_no");
            query.setParameter("roll_no", roll_no);
            Students s = (Students) query.uniqueResult();
            if(s == null){
                session.close();
                return null;
            } else {
                Integer studentID = s.getStudent_id();
                session.close();
                return studentID;
            }

        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
}
