package com.example.erp.controller;

import com.example.erp.bean.*;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("d")
public class DummyTestData {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

//        addDepartments();
//        addEmployee();
//        addCourses();
//        addStudents();
//        registerStudentCourses();
        Students s1 = getStudent("rajat@iiitb.org");
        Courses c1 = getCourse("CS 511");
        System.out.println(s1.getRoll_number());
        System.out.println(c1.getCourse_code());

        return "Done";
    }

    private void registerStudentCourses() {

        List<String> studentsList = Arrays.asList("rajat@iiitb.org","shubhang@iiitb.org","virat@iiitb.org","rohit@iiitb.org","ravinder@iiitb.org","jaspreet@iiitb.org","shikhar@iiitb.org","yuzi@iiitb.org","rahul@iiitb.org","manish@iiitb.org");
        List<String> courseList = Arrays.asList( "CS 511","CS 512", "CS 603", "AI 512", "CS 513");
        studentsList.forEach(s->{
            courseList.forEach(c->{
//                    System.out.println(s+" "+c);
                Students stu= getStudent(s);
                Courses cor = getCourse(c);
                Students_Courses reg = new Students_Courses(stu,cor,3.0);
                try(Session session = SessionUtil.getSession())
                {
                    session.beginTransaction();
                    Integer id  = (Integer)session.save(reg);
                    System.out.println("Department created with id:"+id);
                    session.getTransaction().commit();
                }
                catch (HibernateException e){
                    e.printStackTrace();
                }

            });
        });
    }

    private void addStudents() {


        Students s1 = new Students("MT2020001","Rajat","Kumar","rajat@iiitb.org","default",3.0,12);
        Students s2 = new Students("MT2020002","Shubhang","Drolia","shubhang@iiitb.org","default",3.0,12);
        Students s3 = new Students("MT2020003","Virat","Kohli","virat@iiitb.org","default",3.0,12);
        Students s4 = new Students("MT2020004","Rohit","Sharma","rohit@iiitb.org","default",3.0,12);
        Students s5 = new Students("MT2020005","Ravinder","Jadeja","ravinder@iiitb.org","default",3.0,12);
        Students s6 = new Students("MT2020006","Jasprit","Bumrah","jaspreet@iiitb.org","default",3.0,12);
        Students s7 = new Students("MT2020007","Shikhar","dhawan","shikhar@iiitb.org","default",3.0,12);
        Students s8 = new Students("MT2020008","Rahul","KL","rahul@iiitb.org","default",3.0,12);
        Students s9 = new Students("MT2020009","Yuzi","Chahal","yuzi@iiitb.org","default",3.0,12);
        Students s10 = new Students("MT2020010","Manish","Pandey","manish@iiitb.org","default",3.0,12);
        List<Students> studentsList = Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10);
        ArrayList<Students> l1 = new ArrayList<Students>();
        l1.addAll(studentsList);

        l1.forEach((d)->{
            try(Session session = SessionUtil.getSession())
            {
                session.beginTransaction();
                Integer id  = (Integer)session.save(d);
                System.out.println("Department created with id:"+id);
                session.getTransaction().commit();
            }
            catch (HibernateException e){
                e.printStackTrace();
            }
        });

    }


    private void addStudentToDb(Students s1) {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            Integer id  = (Integer)session.save(s1);
            System.out.println("Student created with id:"+id);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }
    private Students getStudent(String e1) {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            Query query = session.createQuery("from Students where email=:email");
            query.setParameter("email", e1);
            Students s =(Students) query.uniqueResult();
            return s;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    private Courses getCourse(String c1) {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            Query query = session.createQuery("from Courses where course_code=:code");
            query.setParameter("code", c1);
            Courses course =(Courses) query.uniqueResult();
            return course;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
    private void addCourses() {

        String e1 = "murali@iiitb.ac.in";
        String e2 ="rc@iiitb.ac.in";
        String e3 = "v.ramasubramanian@iiitb.ac.in";
        String e4 = "b.thangaraju@iiitb.ac.in";
        Employees emp1 = getEmployee(e1);
        Courses c1 = new Courses("CS 511","Algorithms","Algorithms", 2020,"I",4,150,emp1);
        addCourseToDb(c1);
        Courses c5 = new Courses("CS 512","Advanced Algorithms","Advanced Algorithms", 2020,"I",4,150,emp1);
        addCourseToDb(c5);

        Employees emp2 = getEmployee(e2);
        Courses c2 = new Courses("CS 603","Data Modeling","Data Modeling", 2020,"I",4,150,emp2);
        addCourseToDb(c2);

        Employees emp3 = getEmployee(e3);
        Courses c3 = new Courses("AI 512","Mathematics for Machine Learning","Mathematics for Machine Learning", 2020,"I",4,150,emp3);
        addCourseToDb(c3);

        Employees emp4 = getEmployee(e4);
        Courses c4 = new Courses("CS 513","Software Systems","Software Systems", 2020,"I",4,150,emp4);
        addCourseToDb(c4);

    }

    private void addCourseToDb(Courses c1) {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            Integer id  = (Integer)session.save(c1);
            System.out.println("Course created with id:"+id);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    private Employees getEmployee(String e1) {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            Query query = session.createQuery("from Employees where email=:email");
            query.setParameter("email", e1);
            Employees emp =(Employees) query.uniqueResult();
            return emp;

        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    private void addEmployee() {
        Departments d = getDepartment();
        if(d != null ){
            Employees e1 = new Employees("Muralidhara","V N","murali@iiitb.ac.in","Associate Professor","default", d);
            Employees e2 = new Employees("Chandrashekar","Ramanathan","rc@iiitb.ac.in","Professor","default", d);
            Employees e3 = new Employees("Ramasubramanian","V","v.ramasubramanian@iiitb.ac.in","Professor","default", d);
            Employees e4 = new Employees("Thangaraju","B","b.thangaraju@iiitb.ac.in","Professor","default", d);
            ArrayList<Employees> l1 = new ArrayList<Employees>();
            l1.add(e1);
            l1.add(e2);
            l1.add(e3);
            l1.add(e4);
            l1.forEach((emp)->{
                try(Session session = SessionUtil.getSession())
                {
                    session.beginTransaction();
                    Integer id  = (Integer)session.save(emp);
                    System.out.println("Emp created with id:"+id);
                    session.getTransaction().commit();
                }
                catch (HibernateException e){
                    e.printStackTrace();
                }
            });

        }

    }
    public Departments getDepartment(){
        Departments d = null;
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            d = session.get(Departments.class,1);
            return d;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return d;
    }
    public void addDepartments(){

        Departments d1 =  new Departments("CSE", 20);
        Departments d2 =  new Departments("ECE", 20);
        ArrayList<Departments> l1 = new ArrayList<Departments>();
        l1.add(d1);
        l1.add(d2);

        l1.forEach((d)->{
            try(Session session = SessionUtil.getSession())
            {
                session.beginTransaction();
                Integer id  = (Integer)session.save(d);
                System.out.println("Department created with id:"+id);
                session.getTransaction().commit();
            }
            catch (HibernateException e){
                e.printStackTrace();
            }
        });

    }
}
