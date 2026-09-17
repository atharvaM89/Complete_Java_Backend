package org.cfs.BootP02.controller;

import org.cfs.BootP02.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/welcome")
    public String dataFetchFromdb(){
        return service.getStudentData();
    }
    //for this Method we need the object of Student Service in that one Method get Student data
    //so we will use AutoWire

}
