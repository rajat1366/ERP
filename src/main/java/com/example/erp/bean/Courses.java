package com.example.erp.bean;




import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer course_id;

    @Column(nullable = false,unique = true)
    private String course_code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private Integer credits;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "faculty")
    private Employees employee;

    @ManyToMany
    @JoinTable(name = "Courses_TA", joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Students> students ;


    @OneToMany(mappedBy="course")
    private List<Students_Courses> students_courses;


    @JsonbTransient
    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    @JsonbTransient
    public List<Students_Courses> getStudents_courses() {
        return students_courses;
    }

    public void setStudents_courses(List<Students_Courses> students_courses) {
        this.students_courses = students_courses;
    }

    public Courses() {
    }

    public Courses(String course_code, String name, String description, Integer year, String term, Integer credits, Integer capacity, Employees employee) {
        this.course_code = course_code;
        this.name = name;
        this.description = description;
        this.year = year;
        this.term = term;
        this.credits = credits;
        this.capacity = capacity;
        this.employee = employee;
    }


    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    @Override
    public String toString() {
        return "{" +
                "course_id=" + course_id +
                ", course_code='" + course_code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", term='" + term + '\'' +
                ", credits=" + credits +
                ", capacity=" + capacity +
                ", employee=" + employee +
                '}';
    }
}
