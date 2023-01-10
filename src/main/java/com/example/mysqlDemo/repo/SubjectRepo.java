package com.example.mysqlDemo.repo;

import com.example.mysqlDemo.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, Long > {
}
