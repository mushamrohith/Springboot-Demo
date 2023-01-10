package com.example.mysqlDemo.Controller;

import com.example.mysqlDemo.Entity.Subject;
import com.example.mysqlDemo.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    SubjectRepo subjectRepo;

        @GetMapping("/api/subjects")
        public ResponseEntity<List<Subject>> getSubjects(){
            return new ResponseEntity<>(subjectRepo.findAll(), HttpStatus.OK);
        }

         @GetMapping("/api/subjects/{subId}")
    public ResponseEntity<Subject> getSubject(@PathVariable long subId){     // we mention pathvariable and Optional , when we {subId} in specific Url
             Optional<Subject> subject = subjectRepo.findById(subId);
             if (subject.isPresent()){
                 return new ResponseEntity<>(subject.get(),HttpStatus.OK);
             }
             else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
         }

         @PostMapping("/api/subjects")
    public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject){
            return new ResponseEntity<>(subjectRepo.save(subject),HttpStatus.CREATED);
         }

         @PutMapping("/api/subjects/{subId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable long subId, @RequestBody Subject sub){
            Optional<Subject> subject = subjectRepo.findById(subId);
           if (subject.isPresent()){
               subject.get().setSubName(sub.getSubName());
               return new ResponseEntity<>(subjectRepo.save(subject.get()),HttpStatus.OK);
           }
           else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
         }

         @DeleteMapping("/api/subjects/{subId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable long subId){
            Optional<Subject> subject = subjectRepo.findById(subId);

            if (subject.isPresent()){
                subjectRepo.deleteById(subId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
         }

}
