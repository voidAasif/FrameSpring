package com.example.j004_DbM.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public StudentService studentService;

    // public MainController(StudentService studentService){
    //     this.studentService = studentService;
    // }
    
    @GetMapping
    public ResponseEntity<List<Student>> getData(){
        List<Student> allStudents = studentService.getStudents();
        // allStudents = null; //uncomment this to send error code;
        
        if(allStudents == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //404;
        }
        return ResponseEntity.of(Optional.of(allStudents));
    }

    @GetMapping("{studentName}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentName){
        Student student = studentService.getStudentByName(studentName);
        if(student == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // return ResponseEntity.of(Optional.of(student));
        return ResponseEntity.status(HttpStatus.OK).body(student); // response with status, 200;
    }
    
    @PostMapping
    public ResponseEntity<Student> postData(@RequestBody Student myStudent){
        Student student = null;
        try {
            student = studentService.addStudent(myStudent);
            return ResponseEntity.status(HttpStatus.CREATED).body(student); //201;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500;
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> putData(@PathVariable int studentId, @RequestBody Student myStudent){
        Student student = null;
        try {
            student = studentService.updateStudent(studentId, myStudent);
            return ResponseEntity.status(HttpStatus.OK).body(student); //200;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Boolean> deleteData(@PathVariable int studentId){
        Boolean status = false;
        try {
            status = studentService.deleteStudent(studentId);
            return ResponseEntity.status(HttpStatus.OK).body(status); //200;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(status); //501;
        }
    }
}
