package com.example.j008_serverSideValidation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.j008_serverSideValidation.entity.UserData;

import jakarta.validation.Valid;

@Controller
public class FormController {
    
    @GetMapping("/form")
    public String form(Model model){
        System.out.println("form working");
    
        model.addAttribute("userData", new UserData());

        return "form";
    }

    @PostMapping("/success")
    public String submit(@Valid @ModelAttribute("userData") UserData userDataObj, BindingResult bindingResult){
        
        System.out.println(userDataObj.getUserName()); //log;
        System.out.println(userDataObj.getUserEmail()); //log;
        System.out.println(userDataObj.getTerms()); //log;

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "form";
        }
        
        System.out.println("Success working");
        return "success";
    }
}
