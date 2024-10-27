package com.example.j001_first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(){
        System.out.println("Home fun");
        return "index";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        System.out.println("Test fun");
        return "Working";
    }
}
