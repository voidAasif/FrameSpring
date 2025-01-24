package com.example.j007_thymeleaf.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class ViewController {
    
    @GetMapping("/variable")
    public String variable(Model model){ //model used for dynamically add the content of page or view;
        System.out.println("variable working");

        model.addAttribute("name", "aasif");
        model.addAttribute("date", LocalDate.now());

        return "variable";
    }

    @GetMapping("/iteration")
    public String iteration(Model model){
        System.out.println("iteration working");

        List<String> fruits = List.of("apple", "banana", "grapes", "pineapple", "papaya", "watermelon");

        model.addAttribute("fruits", fruits);

        return "iteration";
    }

    @GetMapping("/condition")
    public String condition(Model model){
        System.out.println("condition working");

        //elvis operator;
        model.addAttribute("isActive", true);

        //if-unless statement;
        model.addAttribute("gender", 'M');

        //switch-case statements;
        model.addAttribute("monthNumber", 12);

        return "condition";
    }

    @GetMapping("/fragment")
    public String fragment(Model model){
        System.out.println("fragment working");
        
        return "fragment";
    }
}
