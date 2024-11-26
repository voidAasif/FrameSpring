package com.example.j004_DbM.controller;

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

import com.example.j004_DbM.pojo.Student;
import com.example.j004_DbM.service.StudentService;

@RestController
@RequestMapping("/home")
public class MainController {

    public StudentService studentService;

    @Autowired
    public MainController(StudentService studentService){
        this.studentService = studentService;
    }
    
    @GetMapping
    public List<Student> getData(){
        return studentService.getStudents();
    }
    
    @PostMapping
    public void postData(@RequestBody Student myStudent){
        studentService.addStudent(myStudent);
    }

    @PutMapping("/{studentId}")
    public void putData(@PathVariable int studentId, @RequestBody Student myStudent){
        studentService.updateStudent(studentId, myStudent);
    }

    @DeleteMapping("/{studentId}")
    public void deleteData(@PathVariable int studentId){
        studentService.deleteStudent(studentId);
    }
}
