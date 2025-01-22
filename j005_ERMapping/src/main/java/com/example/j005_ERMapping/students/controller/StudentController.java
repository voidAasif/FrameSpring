package com.example.j005_ERMapping.students.controller;

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

import com.example.j005_ERMapping.students.entity.Student;
import com.example.j005_ERMapping.students.service.StudentService;


@RestController //work with data;
@RequestMapping("/students") //endpoint of controller;
public class StudentController {

    @Autowired
    public StudentService studentService; //dependency injection;
    
    @GetMapping
    public List<Student> getStudent(){ //get list of student from Service and return it;
        return studentService.listStudents(); 
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student){ //RequestBody accept student as JSON and send it to Service class;
        return studentService.addStudent(student);
    }

    @PutMapping("/{studentRollNo}") //get student rollNo from path and set into PathVariable;
    public Student putStudent(@PathVariable int studentRollNo, @RequestBody Student student){
        return studentService.updateStudent(studentRollNo, student);
    }

    @DeleteMapping("/{studentRollNo}") //same like post mapping;
    public Student deleteStudent(@PathVariable int studentRollNo){
        return studentService.deleteStudent(studentRollNo);
    }

}

/* sample json;
 
{
    "rollNo": 12,
    "name": "aasif",
    "grade": "A",
    "subject": {
        "subjectName": "java",
        "subjectComplete": true
    }
}

 */
