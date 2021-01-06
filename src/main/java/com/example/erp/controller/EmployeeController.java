package com.example.erp.controller;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Employees;
import com.example.erp.bean.Students;
import com.example.erp.bean.Students_Courses;
import com.example.erp.service.CoursesService;
import com.example.erp.service.EmployeesService;
import com.example.erp.service.StudentService;
import com.example.erp.utils.ErrorMessage;
import com.example.erp.utils.SuccessMessage;
import org.json.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Path("emp")
public class EmployeeController {
    Employees emp = null;
    EmployeesService emplService = new EmployeesService();
    StudentService studentService = new StudentService();
    CoursesService coursesService = new CoursesService();

    private void checkSetEmp(String email){
        if(emp == null){
            emp = emplService.getEmployee(email);
        }
    }
    @GET
    @Path("/getCourses/{id}/{email}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@PathParam("id") int id,@PathParam("email") String email) throws URISyntaxException {
        System.out.println(id+" "+email);
        checkSetEmp(email);
        List<Courses> courses =  emplService.getEmpCourses(emp.getEmployee_id());
        return Response.ok().entity(courses).build();
    }

    @POST
    @Path("/addTA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTA(Courses course) throws URISyntaxException {

        String roll_no = course.getStudents().get(0).getRoll_number();
        Integer studentId = null;
        studentId = studentService.getStudentIdFromRollNo(roll_no);

        if(studentId == null){
            ErrorMessage errorMessage = new ErrorMessage("Invalid Roll No");
            return Response.ok().entity(errorMessage).build();
        }

        if(coursesService.checkStudentInCourseTAList(course.getCourse_id(), studentId)){
            ErrorMessage errorMessage = new ErrorMessage("Student already registerd as TA in this course");
            return Response.ok().entity(errorMessage).build();
        } else {
                if(coursesService.registerTA(course.getCourse_id(),studentId)){
                    SuccessMessage successMessage = new SuccessMessage("Record inserted");
                    return Response.ok().entity(successMessage).build();
                } else {
                    ErrorMessage errorMessage = new ErrorMessage("Error Occured");
                    return Response.ok().entity(errorMessage).build();
                }
        }

    }

    @POST
    @Path("/listTA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listTA(Courses course) throws URISyntaxException {
        System.out.println(course.getCourse_id());
        Courses course1 = coursesService.getCourseById(course.getCourse_id());
        List<Students> courseTA = coursesService.getCourseTAList(course.getCourse_id())   ;

        for(int i =0;i<courseTA.size();i++){
            courseTA.get(i).setCourses(new ArrayList<Courses>());
            courseTA.get(i).setStudents_courses(new ArrayList<Students_Courses>());
        }
        if(courseTA.size()==0){
            ErrorMessage errorMessage = new ErrorMessage("No Students is assigned as TA");
            return Response.ok().entity(errorMessage).build();
        }
        return Response.ok().entity(courseTA).build();
//        return Response.ok().entity(course1.getStudents()).build();

    }

    @POST
    @Path("/delTA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delTA(Courses course) throws URISyntaxException {


        String roll_no = course.getStudents().get(0).getRoll_number();

        Integer studentID = studentService.getStudentIdFromRollNo(roll_no);
        if(studentID == null){
            ErrorMessage errorMessage = new ErrorMessage("Invalid Roll No");
            return Response.ok().entity(errorMessage).build();
        }

        if( coursesService.checkStudentInCourseTAList(course.getCourse_id(),studentID)){
            if(coursesService.detTA(course.getCourse_id(),studentID)){
                SuccessMessage successMessage = new SuccessMessage("TA Successfully removed");
                return Response.ok().entity(successMessage).build();
            } else {
                ErrorMessage errorMessage = new ErrorMessage("Error Occured");
                return Response.ok().entity(errorMessage).build();
            }
        } else {
            ErrorMessage errorMessage = new ErrorMessage("This student is not the register as TA in this course");
            return Response.ok().entity(errorMessage).build();
        }

    }

}
