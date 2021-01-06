package com.example.erp.bean;

import javax.persistence.*;

@Entity
@Table(name= "Employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

    @Column(nullable = false)
    private String first_name;

    private String last_name;

    @Column(nullable = false,unique = true)
    private String email;

    private String title;

    private String photograph_path;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department")
    private Departments department;

    public Employees() {
    }

    public Employees(String first_name, String last_name, String email, String title, String photograph_path, Departments department) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.title = title;
        this.photograph_path = photograph_path;
        this.department = department;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotograph_path() {
        return photograph_path;
    }

    public void setPhotograph_path(String photograph_path) {
        this.photograph_path = photograph_path;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employee_id=" + employee_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", photograph_path='" + photograph_path + '\'' +
                '}';
    }
}
