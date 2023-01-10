package com.example.mysqlDemo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subId;
    private String subName;



    // joining tables
    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledSubjects")
    private Set<Teacher> teacher = new HashSet<>();

    public Set<Teacher> getTeacher() {       //getter
        return teacher;
    }


    public Subject(long id, String subName) {
        this.subId = id;
        this.subName = subName;
    }
    public Subject(){
        super();
    }


    public long getSubId() {
        return subId;
    }

    public void setSubId(long subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + subId +
                ", subName='" + subName + '\'' +
                '}';
    }
}
