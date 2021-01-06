package com.example.erp.bean;

import javax.persistence.*;

@Entity
@Table(name = "Students_Courses")
public class Students_Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Students student;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Courses course;

    private Double grade;

    public Students_Courses() {
    }

    public Students_Courses(Students student, Courses course, Double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}