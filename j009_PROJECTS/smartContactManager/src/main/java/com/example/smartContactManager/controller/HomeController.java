package com.example.smartContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.smartContactManager.dao.UserRepository;
import com.example.smartContactManager.entities.User;
import com.example.smartContactManager.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository; //to use JPA methods;
    
    @GetMapping("/")
    public String home(Model model){ //show home page;
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @GetMapping("/about") //show about page;
    public String about(Model model){
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    @GetMapping("/signup") //show signup page;
    public String signup(Model model){
        model.addAttribute("title", "Register - Smart Contact Manager");
        model.addAttribute("user", new User()); //set new empty user;
        return "signup";
    }

    //this handler for registering user;
    
    @PostMapping("/do_register") //execute when submit button clicked;
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession httpSession){
        //ModelAttribute is used to get user from form;
        //RequestParm is used to get any value from html page;
        //HttpSession is used to display the message;

        if (bindingResult.hasErrors()){
            System.out.println("**************************************");
            System.out.println(bindingResult.getAllErrors()); //log;
            System.out.println("**************************************");

            return "signup"; //error- display same form;
        }

        try {
            user.setEnabled(true); //set empty values of user;
            user.setRole("USER_ROLE");
            user.setImage("default.png");

            System.out.println(model); //logs;
            System.out.println(agreement);
            System.out.println();

            if (!agreement) { //if user not check the checkbox;
                System.out.println("Please accept the terms and conditions first");
                throw new Exception("Please accept the terms and conditions first"); //throw exception and jump to catch block;
            }

            //temporary block the DB for validation testing;
            // User savedUser = userRepository.save(user); //save user into DB;
            // System.out.println("saved user: "+ savedUser); //log;

            //now we can access it in HTML by (${session.className.attribute}), we take content to store message and type to append class name in css;
            httpSession.setAttribute("message", new Message("Data submit successfully", "alert-success"));
            
            model.addAttribute("user", new User()); //if all data is correct then empty tha form;

        } catch (Exception e) {
            e.printStackTrace();

            //e.getMessage append the exception message;
            httpSession.setAttribute("message", new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));
            //if exception occurs then display old user data into form;
            model.addAttribute("user", user);
        }
        
        return "signup"; //success- change view; currently not changed;
    }
}


/*
    1. @ModelAttribute("user") User user
            Binds form data (usually from a form submission in HTML) to the User object.
            The form fields should have names corresponding to the properties of the User class.
    2. @RequestParam(value = "agreement", defaultValue = "false") boolean agreement
            Binds a specific request parameter (agreement) from the request to the agreement boolean variable.
            The defaultValue = "false" ensures that if the request parameter is not provided, it defaults to false.
    3. Model model
            Allows adding attributes to the model, which will be accessible in the view template (like Thymeleaf or JSP).
    4. HttpSession httpSession
            Used to store and retrieve session-specific information.
            Can be used to pass messages between the controller and the view.
 */