package com.example.erp.dao.implementation;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.bean.Students_Courses;
import com.example.erp.dao.CoursesDao;
import com.example.erp.utils.ErrorMessage;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import java.util.List;

public class CoursesDaoImpl implements CoursesDao {

    @Override
    public boolean saveCourse(Courses course) {
        Session session = SessionUtil.getSession();
        try
        {
            Transaction transaction =  session.beginTransaction();
            session.update(course);
            transaction.commit();
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Courses getCourseByCourseCode(String course_code){
        Session session = SessionUtil.getSession();
        Courses course = null;
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Courses where course_code=:course_code");
            query.setParameter("course_code", course_code);
            course =(Courses) query.uniqueResult();
            return course;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Courses  getCourseById(Integer id){
        Courses course = null;
        Session session = SessionUtil.getSession();
        try
        {
            return session.get(Courses.class,id);

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerTA(Integer course_id, Integer student_id) {
        Session session = SessionUtil.getSession();
        try {

            Transaction transaction =  session.beginTransaction();
            Courses courses =  session.get(Courses.class, course_id);
            Students student = session.get(Students.class,student_id);

            courses.getStudents().add(student);
            student.getCourses().add(courses);

            session.save(courses);
            session.save(student);

            transaction.commit();
            session.close();
            return true;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    public boolean delTA(Integer course_id, Integer studentId) {
        Session session = SessionUtil.getSession();
        try {

            Transaction transaction =  session.beginTransaction();
            Courses courses =  session.get(Courses.class, course_id);
            Students student = session.get(Students.class,studentId);

            courses.getStudents().removeIf(t -> t.getStudent_id() == studentId);
            student.getCourses().removeIf(t-> t.getCourse_id() == course_id);

            session.save(courses);
            session.save(student);
            transaction.commit();
            session.close();
            return true;

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<Students_Courses> getcourseStudents(Integer course_id) {
        Session session = SessionUtil.getSession();
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from Students_Courses where course_id=:course_id");
            query.setParameter("course_id", course_id);
            List<Students_Courses> list  =query.list();
            session.close();
            return list;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Students> getCourseTAList(Integer course_id) throws Exception {
        Session session = SessionUtil.getSession();
        try
        {
            Courses course  = session.get(Courses.class,course_id);
            List<Students> list  = course.getStudents();
            if(list.size()>0)
                System.out.println(list.get(0).getRoll_number());

            session.close();
            return list;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkStudentInCourseTAList(Integer course_id, Integer studentId) {
        Session session = SessionUtil.getSession();
        try
        {
            Courses courses = session.get(Courses.class,course_id);
            List<Students> studentTAList = courses.getStudents();

            for(int i =0 ; i<studentTAList.size();i++){
                if(studentTAList.get(i).getStudent_id() == studentId){
                    ErrorMessage errorMessage = new ErrorMessage("Student already registerd as TA in this course");
                    session.close();
                    return true;
                }
            }
            session.close();
            return false;

        }
        catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }
}
