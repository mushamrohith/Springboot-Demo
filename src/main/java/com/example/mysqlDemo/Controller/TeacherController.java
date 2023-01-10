package com.example.mysqlDemo.Controller;


import com.example.mysqlDemo.Entity.Subject;
import com.example.mysqlDemo.Entity.Teacher;
import com.example.mysqlDemo.repo.SubjectRepo;
import com.example.mysqlDemo.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController {

    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    SubjectRepo subjectRepo;

    @GetMapping("/api/teacher")                 //for getting all details
    public ResponseEntity<List<Teacher>> getTeachers(){
        return new ResponseEntity<>(teacherRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/teacher/{empId}")           // to get details of specific teacher
    public ResponseEntity<Teacher> getTeacher(@PathVariable long empId){
        Optional<Teacher> teacher = teacherRepo.findById(empId);       // calling details through mentioned empId

        if(teacher.isPresent()){
            return new ResponseEntity<>(teacher.get(),HttpStatus.OK);
        }

        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/teacher")                                // inserting new teacher details
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherRepo.save(teacher), HttpStatus.CREATED);
    }

    @PutMapping("/api/teacher/{empId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable long empId, @RequestBody Teacher teach){
        Optional<Teacher> teacher = teacherRepo.findById(empId);
        if (teacher.isPresent()){
            teacher.get().setEmpName(teach.getEmpName());  //assigning updated data
            teacher.get().setEmpEmail(teach.getEmpEmail());
            teacher.get().setBranch(teach.getBranch());
            return new ResponseEntity<>(teacherRepo.save(teacher.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/teacher/{empId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable long empId){
        Optional<Teacher> teacher = teacherRepo.findById(empId);
        if (teacher.isPresent()){
            teacherRepo.deleteById(empId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/teacher/{teacherId}/subjects/{subjectId}")
    public Teacher enrollTeacherToSubject(@PathVariable long teacherId, @PathVariable long subjectId){
        Teacher teacher = teacherRepo.findById(teacherId).get();
        Subject subject = subjectRepo.findById(subjectId).get();
        teacher.enrollSubject(subject);
        return teacherRepo.save(teacher);

    }

}
