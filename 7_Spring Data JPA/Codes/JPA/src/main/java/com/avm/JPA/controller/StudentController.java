package com.avm.JPA.controller;


import com.avm.JPA.entity.Student;
import com.avm.JPA.repo.StudentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepo studentRepo;

    //Case 1 -> if we didn't mention Final then we need to Mention AutoWire Above the objec Creation

    /* case 2
    * If we want to mention final then we must initialize*/
    //so here we use Constructor for Dependency Injection
    public StudentController(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }

    //we just need to do this
    //for PostAPI
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }
    //for post we need to save this inside a DB

    //for Get API
    @GetMapping("students")
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }
    //to get that data we need to get all that data from the DB

    //for put API
    @PutMapping
    public Student updateStudent(@RequestParam Long id, @RequestBody Student student){
        Student s=studentRepo.findAllById(id)
                .orElseThrow(()->new RuntimeException("Student Not Found")); //Defining Exception
        s.setName(student.getName());
        s.setEmail(student.getEmail());

        return studentRepo.save(s);
    }

    @PatchMapping Mapping
    public Student patchStudent(@RequestParam Long id, @RequestBody Student student){
        Student s=studentRepo.findAllById(id)
                .orElseThrow(()->new RuntimeException("Student Not Found")); //Defining Exception
        s.setName(student.getName());
        s.setEmail(student.getEmail());

        return studentRepo.save(s);
    }

}

