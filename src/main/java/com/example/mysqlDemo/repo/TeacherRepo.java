package com.example.mysqlDemo.repo;

import com.example.mysqlDemo.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
}
