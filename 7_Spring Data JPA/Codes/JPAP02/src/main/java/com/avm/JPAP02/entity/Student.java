package com.avm.JPAP02.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //ex of many to many
    @ManyToMany
    @JoinTable(
            name="student_course",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    @JsonIgnoreProperties("student")
    public Set<Course> courses=new HashSet<>();

    //ex of many to One
    @ManyToOne(fetch = FetchType.LAZY)// for making datat load faster
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // ex of One to One
    @OneToOne(mappedBy ="student", cascade = CascadeType.ALL)
    private Laptop laptop;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}

