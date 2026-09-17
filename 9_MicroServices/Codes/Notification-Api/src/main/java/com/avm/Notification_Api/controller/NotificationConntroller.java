package com.avm.Notification_Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NotificationConntroller {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/notification")
    public String Notification(){
        return "Email Send to user";
    }

    @Autowired
    private ProductAPI productAPI;

    @GetMapping("/notification/product")
    public String getproduct(){
        String s1="Hello form notification API";
        //String s2=restTemplate.getForObject("http://localhost:9091/place",String.class);
        String s2=productAPI.invokeProductAPI();
        return s1+" "+s2;
    }

}
