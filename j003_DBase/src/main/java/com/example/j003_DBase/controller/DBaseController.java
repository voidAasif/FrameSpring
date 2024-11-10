package com.example.j003_DBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.j003_DBase.entity.Student;
import com.example.j003_DBase.services.StudentServices;

@RestController
@RequestMapping("/home")
public class DBaseController {

    //used service here;
    private final StudentServices studentServices;

    @Autowired //also work without it;
    public DBaseController(StudentServices studentServices){
        // this.studentServices = new StudentServices(); //manual instance;
        this.studentServices = studentServices; //auto - Autowired;
    }
    
    @GetMapping // this is a default mapping it means - url/home;
    public List<Student> getRequest(){
        System.out.println("GetMapping work");
        return studentServices.seeData();
    }

    @PostMapping
    public void postRequest(@RequestBody Student myStudent){

    }

    @PutMapping("/{stuId}")
    public void putRequest(@PathVariable int stuId, @RequestBody Student myStudent){
        
    }

    @DeleteMapping("/{stuId}")
    public void deleteRequest(@PathVariable int stuId, @RequestBody Student myStudent){

    }
}
