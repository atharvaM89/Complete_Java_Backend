package com.avm.JPA.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // to Create a table in DB of name Student
public class Student {
    @Id // for making it as Primary id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;

    public Student(){

    }

    public Student(String name, String email) {
        //In this Constructor we didn't define a Id Because Id is autoIncremented by DB so no need to handle it
        this.name = name;
        this.email = email;
    }

    //we also need Getter and Setter for Id to Retreival and Showing details of ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
