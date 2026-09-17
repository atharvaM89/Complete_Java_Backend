package org.example;

public class Student {
    public Course course;  //we need object of Course type


    //This is for Constructor Dependency
    public Student() {
    }

    public Student(Course course) {
        this.course = course;
    }

    //This is For Setter Dependency
    public void setCourse(Course course) {
        this.course = course;
    }

    public void study(){
        int start= course.enroll();
        if(start>=1){
            System.out.println("Journey Started");
        }
        else{
            System.out.println("Payment Failed... ");
        }
    }
}
