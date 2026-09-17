package com.avm.SpringSecurityP01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping("/contactUs")
    public String contactUs(){
        return "Contact us at 8600511467";
    }

    @GetMapping("/transfer")
    private String transferMoney(){
        return "Money Transfer Sucessfully";
    }

    @GetMapping("/admin")
    private String admin(){
        return "This is admin panel";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "All rights Reserved under RBI bank  ";
    }
}
