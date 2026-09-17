package com.avm.SpringSecurity_p04.controller;


import com.avm.SpringSecurity_p04.request.UserListRequest;
import com.avm.SpringSecurity_p04.request.UserRequest;
import com.avm.SpringSecurity_p04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private UserService service;


    @PostMapping("/addUsers")
    public String addUser(@RequestBody UserListRequest request){
        service.saveUsers(request.getUsers());
        return "Users Added Sucessfully...";
    }

    @GetMapping("/public")
    public String publicMethod(){
        System.out.println("Public Method Called");
        return "This is Public Method";
    }

    @GetMapping("/admin")
    public String adminMethod(){
        System.out.println("Admin Method Called");
        return "This is admin Method";
    }

    @GetMapping("/user")
    public String userMethod(){
        System.out.println("User Method Called");
        return "This is User Method";
    }


}
