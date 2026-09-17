package com.avm.Security_p03.controller;

import com.avm.Security_p03.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    AccountService accountService;


    @GetMapping("/balance")
    public String getBalnce(){
        return accountService.getBalance();
    }

    @PostMapping("/close")
    public String closeAcc(){
        return accountService.closeAcc();
    }

    @GetMapping("/about")
    public String about(){
        return "This is about session";
    }
}

