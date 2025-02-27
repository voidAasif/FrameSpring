package com.example.smartContactManager.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.smartContactManager.dao.UserRepository;
import com.example.smartContactManager.entities.Contact;
import com.example.smartContactManager.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal){
        String username = principal.getName();

        User user = userRepository.getUserByUserName(username);

        System.out.println(user);
        
        model.addAttribute("user", user);
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("title", "Dashboard");
        return "normal/userDashboard";
    }

    @GetMapping("/addContact")
    public String addContact(Model model){
        model.addAttribute("title", "Add Contact");
        model.addAttribute("contact", new Contact());
        return "normal/addContact";
    }

    @PostMapping("/process_contact")
    public String processContact(@ModelAttribute("contact") Contact contact, @RequestParam("imagee") MultipartFile file){
        contact.setImage(file.getOriginalFilename());
        System.out.println(contact);
        return "normal/addContact";
    }
}
