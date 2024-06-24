package com.mayo.insurance.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/hello")
    public String sayHello(){
        return "testing";
    }
}
