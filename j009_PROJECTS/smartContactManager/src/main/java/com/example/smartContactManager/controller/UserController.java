package com.example.smartContactManager.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
    public String processContact(@ModelAttribute("contact") Contact contact, @RequestParam("profileImage") MultipartFile file, Principal principal){
        
        try {   
            String username = principal.getName(); //get current username;
            User user = userRepository.getUserByUserName(username); //find user by username;
            contact.setUser(user); //multidirectional mapping, so set user in contact;
            user.getContacts().add(contact); //add contact in contact list;


            //process profile image;
            if(file.isEmpty()){
                System.out.println("Image file is empty"); //log;
            }
            else {
                contact.setImage(file.getOriginalFilename()); //set image name into contact;

                String uploadedImagePath = new ClassPathResource("/static/uploads/").getFile().getAbsolutePath(); //get path to store image;

                Files.copy(file.getInputStream(), Path.of(uploadedImagePath + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING); //copy input image into given path;

            }
            

            userRepository.save(user); //save user with contact in DB;
            System.out.println(contact); //log;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occur when saving contact in DB");
        }


        return "normal/addContact";
    }
}
