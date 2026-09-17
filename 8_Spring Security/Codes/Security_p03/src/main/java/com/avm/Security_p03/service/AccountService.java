package com.avm.Security_p03.service;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @PreAuthorize("hasRole('USER')") // now this only user can acces it
    //@PreAuthorize("isAuthenticated")  //this is access to user and ADMIN but is require login credentials
    public String getBalance(){
        return "Your banalce is =  8000000";
    }

    @PreAuthorize("hasRole('ADMIN')")  //this is accessed onlky ny user
    public String closeAcc(){
        return "Your Acc is Closed";
    }
}
