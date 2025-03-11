package com.example.smartContactManager.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.smartContactManager.dao.UserRepository;
import com.example.smartContactManager.dao.ContactRepository;
import com.example.smartContactManager.entities.Contact;
import com.example.smartContactManager.entities.User;
import com.example.smartContactManager.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

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
    public String processContact(@ModelAttribute("contact") Contact contact, @RequestParam("profileImage") MultipartFile file, Principal principal, HttpSession httpSession){
        
        try {   
            String username = principal.getName(); //get current username;
            User user = userRepository.getUserByUserName(username); //find user by username;
            contact.setUser(user); //multidirectional mapping, so set user in contact;
            user.getContacts().add(contact); //add contact in contact list;


            //process profile image;
            if(file.isEmpty()){
                System.out.println("Image file is empty"); //log;
                contact.setImage("default.png");
            }
            else {
                contact.setImage(file.getOriginalFilename()); //set image name into contact;

                String uploadedImagePath = new ClassPathResource("/static/uploads/").getFile().getAbsolutePath(); //get path to store image;

                Files.copy(file.getInputStream(), Path.of(uploadedImagePath + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING); //copy input image into given path;

            }
            

            userRepository.save(user); //save user with contact in DB;
            System.out.println(contact); //log;

            //success message;
            httpSession.setAttribute("message", new Message("Contact Add Successfully", "alert-success"));

        } catch (Exception e) {
            //error message;
            httpSession.setAttribute("message", new Message("Error while adding Contact", "alert-danger"));

            e.printStackTrace();
            System.out.println("Exception occur when saving contact in DB");
        }


        return "normal/addContact";
    }

    //apply pagination using pathVariable;
    //we need per page 5 contact;
    //and current page;
    @GetMapping("/view_contact/{currentPage}")
    public String viewContact(@PathVariable("currentPage") Integer currentPage, Model model, Principal principal){
        model.addAttribute("title", "View-Contact");

        String currentUserName = principal.getName(); //find current username by principal;
        User currentUser = userRepository.getUserByUserName(currentUserName); //find current user by username;
        int currentUserId = currentUser.getUserId(); //find user id form current user;

        //Pageable hold two parameters | PageRequest.of take two values which holds by Pageable; 
        //1. pageNumber zero-based page number, must not be negative.
        //2. pageSize the size of the page to be returned, must be greater than 0.
        Pageable pageable = PageRequest.of(currentPage, 5);

        //page is the subclass of List, used to implement pagination;
        Page<Contact> contactList = contactRepository.findByUserUserId(currentUserId, pageable); //find list of contact of current user id;

        model.addAttribute("contactList", contactList); //set contact list into contact;

        //set attributes to use page navigation in view_contact.html;
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", contactList.getTotalPages()); //Page has a method which return total number of pages, we no need to divide it manually;
        
        return "normal/view_contact";
    }

    //display contact details;
    @GetMapping("/contact/{contactId}")
    public String displayContact(@PathVariable("contactId") int contactId, Model model, Principal principal){

        model.addAttribute("title", "Contact-Details");

        //fetch contact form db;
        Contact contact = contactRepository.findByContactId(contactId); //find contact by given contactId;

        //fix security bug: bug(access other contact);
        String currentUserName = principal.getName(); //find current username by principal;
        User currentUser = userRepository.getUserByUserName(currentUserName); //find current user by username;
        int currentUserId = currentUser.getUserId(); //find user id form current user;

        if(currentUserId == contact.getUser().getUserId())
            model.addAttribute("contact", contact); //add contact into template;

        return "normal/contact";
    }
}
