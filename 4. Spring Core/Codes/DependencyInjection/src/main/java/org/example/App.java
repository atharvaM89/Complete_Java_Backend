package org.example;

public class App {

    public static void main(String[] args) {
        Student s=new Student();
        s.course=new DSA_Course();

        //for Setter Dependency
        /*Course course=new DSA_Course();
        s.setCourse(course);*/



        s.study();
    }
}
