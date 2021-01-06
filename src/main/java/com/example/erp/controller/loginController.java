package com.example.erp.controller;


import com.example.erp.bean.Employees;
import com.example.erp.bean.Students;
import com.example.erp.service.EmployeesService;
import com.example.erp.service.StudentService;
import org.json.JSONObject;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("login")
public class loginController {

    StudentService studentService = new StudentService();
    EmployeesService empService = new EmployeesService();

    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginStudent(String request) throws URISyntaxException {

        JSONObject obj = new JSONObject(request);
        String email = (String)obj.get("email");
        System.out.println(email);


        Students student = studentService.getStudent(email);
        Employees employee = empService.getEmployee(email);

        JSONObject res = new JSONObject();
        res.put("error", "InvalidUser");

        if(student == null && employee == null){
            return Response.ok(res).build();
        } else {

            if(student != null){
                return Response.ok().entity(student).build();
            }else if (employee != null) {
                return Response.ok().entity(employee).build();
            }
            return Response.ok(res).build();

        }

    }
}
