package com.example.cicdtest.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {

    @GetMapping(path = "/")
    public String test(){
        return "test";
    }
}
