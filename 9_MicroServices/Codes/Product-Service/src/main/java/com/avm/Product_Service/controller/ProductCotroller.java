package com.avm.Product_Service.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCotroller {

    @Autowired
    private Environment env;

    @GetMapping("/Place")
    public String placeOrder(){
        String port=env.getProperty("Server post");
        return "Running on port "+port+"Your order is placed";
    }

    @GetMapping("/product/{name}")
    public String OrderWithName(@PathVariable String name){
        return "Hello "+name+" Your Order Done ";
    }
}
