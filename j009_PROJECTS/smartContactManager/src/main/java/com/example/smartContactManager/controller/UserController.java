package com.example.smartContactManager.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.smartContactManager.dao.UserRepository;
import com.example.smartContactManager.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String username = principal.getName();

        User user = userRepository.getUserByUserName(username);

        System.out.println(user);

        model.addAttribute("user", user);

        return "normal/userDashboard";
    }
}
