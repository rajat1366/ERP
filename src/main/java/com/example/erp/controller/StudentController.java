package com.example.erp.controller;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.bean.Students_Courses;
import com.example.erp.service.CoursesService;
import com.example.erp.service.StudentService;
import com.example.erp.utils.ErrorMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("stu")
public class StudentController {

    StudentService studentService = new StudentService();
    CoursesService coursesService = new CoursesService();

    @GET
    @Path("/getTACourses/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@PathParam("id") int studentId) throws URISyntaxException {
        System.out.println(studentId);
        Students students = studentService.getStudentById(studentId);
        if(students.getCourses().size()>0){
            return Response.ok().entity(students.getCourses()).build();
        } else{
            ErrorMessage errorMessage = new ErrorMessage("You are assigned as TA");
            return Response.ok().entity(errorMessage).build();
        }

    }

    @POST
    @Path("/getCourseStu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response courseStudents(Courses course) throws URISyntaxException {
        System.out.println(course.getCourse_id());
        List<Students_Courses>  students_courses = coursesService.getCourseStudents(course.getCourse_id());
        if(students_courses.size()>0){
            return Response.ok().entity(students_courses).build();
        } else {
            ErrorMessage errorMessage = new ErrorMessage("No Student Registered");
            return Response.ok().entity(errorMessage).build();
        }

    }

}
