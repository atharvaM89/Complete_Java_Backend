package com.avm.JPA.repo;

import com.avm.JPA.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long>
    //Inside this we give 2 inputs -> Kis ke leye bna rhe hai, aur yuski primary key kya hai
{
}
