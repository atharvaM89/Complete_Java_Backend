package com.avm.JPAP02.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "course")
    //we are consider our course class as owning property so the mapping will be done by this class
    @JsonIgnoreProperties("course")
    private Set<Student> students=new HashSet<>();
}
