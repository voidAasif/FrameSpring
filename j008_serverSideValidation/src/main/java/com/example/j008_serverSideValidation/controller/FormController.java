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
    
    @GetMapping("/form") //display form.html;
    public String form(Model model){
        System.out.println("form working");
    
        model.addAttribute("userData", new UserData()); //1. Data Binding:
                                                                        //Thymeleaf uses the th:object="${userData}" attribute in the form to bind the form's input fields to properties of the UserData object.

                                                                        //2. Pre-filling Form Values:
                                                                        //If the UserData object already had values, those values would be pre-filled in the form fields.

                                                                        //3. Validation Feedback:
                                                                        //Spring MVC uses the same object to validate input and bind errors back to the view. This helps in error handling and user feedback.

        return "form";
    }

    @PostMapping("/success") //display success.html;
    public String submit(@Valid @ModelAttribute("userData") UserData userDataObj, BindingResult bindingResult){ //@ModelAttribute is used for get values of object from form.html;
                                                                                                                //@Valid is used to trigger hibernate validation;
                                                                                                                //BindingResult with the help of it we se the error in validation if occur;
        
        System.out.println(userDataObj.getUserName()); //log;
        System.out.println(userDataObj.getUserEmail()); //log;
        System.out.println(userDataObj.getTerms()); //log;

        if (bindingResult.hasErrors()) { // if any error occur then display form again;
            System.out.println(bindingResult);
            return "form";
        }
        
        System.out.println("Success working");
        return "success"; //then return success page;
    }
}


/*
 * Example Flow
        Display Form: new UserData() initializes empty fields in the form.
        Form Submission: When the form is submitted, Spring MVC automatically maps the input values to the properties of UserData.
        Validation and Redisplay: If validation fails, the UserData object with its current state (including errors) is passed back to the form, preserving user input.
 */
