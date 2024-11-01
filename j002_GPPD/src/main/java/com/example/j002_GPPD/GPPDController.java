package com.example.j002_GPPD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.j002_GPPD.entity.Student;



@RestController // now this class works as a controller;
@RequestMapping("/home") // path mapping for entire class;
public class GPPDController {
    
    //entity;
    Map<Integer, Student> studentList = new HashMap<>(); // virtual DB;
    
    @GetMapping // this is a default mapping it means - url/home;
    public ArrayList<Student> seeData(){
        System.out.println("return list of students"); //log;
        return new ArrayList<>(studentList.values()); //convert map into arrayList;
    }

    @PostMapping
    public void createData(@RequestBody Student myStudent){ //RequestBody is used to get data form various request;
        studentList.put(myStudent.getStudentId(), myStudent); //key, value; (key = stuId && value = stu);
        System.out.println("New Student Added"); //log;
    }

    @PutMapping("/{studentId}") //get value from url;
    public void updateData(@PathVariable int studentId, @RequestBody Student myStudent){ //PathVariable - set value of url into studentId
        studentList.put(studentId, myStudent); //overwrite value;
        System.out.println("Update"); //log;
    }

    @DeleteMapping
    public void deleteMapping(@RequestBody Student myStudent){
        studentList.remove(myStudent.getStudentId()); //remove value from map;
        System.out.println("Delete"); //log;
    }
}
