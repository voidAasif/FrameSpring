package com.example.j010_JWT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/welcome")
    public String welcome(){
        return "this is private page";
    }
}
