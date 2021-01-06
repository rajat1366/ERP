package com.example.erp.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer student_id;

    @Column(nullable = false, unique = true)
    private String roll_number;

    @Column(nullable = false)
    private String first_name;

    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;


    private String photograph_path;

    @Column(nullable = false)
    private Double cgpa;

    @Column(nullable = false)
    private Integer total_credits;


    @ManyToMany(mappedBy = "students")
    private List<Courses> courses;


    @OneToMany(mappedBy="student")
    private List<Students_Courses> students_courses;

    @JsonbTransient
    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    @JsonbTransient
    public List<Students_Courses> getStudents_courses() {
        return students_courses;
    }

    public void setStudents_courses(List<Students_Courses> students_courses) {
        this.students_courses = students_courses;
    }



    public Students() {
    }

    public Students(String roll_number, String first_name, String last_name, String email, String photograph_path, Double cgpa, Integer total_credits) {
        this.roll_number = roll_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.photograph_path = photograph_path;
        this.cgpa = cgpa;
        this.total_credits = total_credits;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotograph_path() {
        return photograph_path;
    }

    public void setPhotograph_path(String photograph_path) {
        this.photograph_path = photograph_path;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getTotal_credits() {
        return total_credits;
    }

    public void setTotal_credits(Integer total_credits) {
        this.total_credits = total_credits;
    }



    @Override
    public String toString() {
        return "{" +
                "student_id=" + student_id +
                ", roll_number='" + roll_number + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", photograph_path='" + photograph_path + '\'' +
                ", cgpa=" + cgpa +
                ", total_credits=" + total_credits +
                '}';
    }
}
