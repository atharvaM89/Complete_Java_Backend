package org.cfs.BootP02.service;

import org.cfs.BootP02.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String getStudentData(){
        return studentRepository.getStudentData();
    }
}
