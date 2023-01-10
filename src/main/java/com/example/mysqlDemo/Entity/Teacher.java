package com.example.mysqlDemo.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long empId;
    private String empName;
    private String empEmail;
    private String branch;



    // joining table
    @ManyToMany
    @JoinTable(
            name = "subjects_enrolled",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name ="subject_id")
    )
    private Set<Subject> enrolledSubjects = new HashSet<>();

    public Set<Subject> getEnrolledSubjects() {     //getter
        return enrolledSubjects;
    }


    public Teacher(long empId, String empName, String empEmail, String branch) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.branch = branch;
    }

    public Teacher(){
        super();
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

    public void enrollSubject(Subject subject) {
        enrolledSubjects.add(subject);
    }
}
