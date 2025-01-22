package com.example.j005_ERMapping.students.service;

import java.util.List;

import com.example.j005_ERMapping.students.entity.Student;
import com.example.j005_ERMapping.students.repo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    
    public List<Student> listStudents(){
        return studentRepo.findAll();
    }

    public Student addStudent(Student student){
        return studentRepo.save(student);
    }

    public Student updateStudent(int studentRollNo, Student student){

        Student newStudent = studentRepo.findById(studentRollNo)
        .orElseThrow(()-> new RuntimeException("Student not found"));

        //Optional contain student or nothing;
        //if optional is empty it will return nothing;
        //if optional is not empty it will return student;
        //findById return Optional of student which may or may not contain student;
        //after chaining orElseThrow this method return student if present and in case of nothing it will throw exception;

        newStudent.setRollNo(student.getRollNo()); //set new attributes into student;
        newStudent.setName(student.getName());
        newStudent.setGrade(student.getGrade());
        newStudent.setSubject(student.getSubject());

        return studentRepo.save(newStudent); //save new student;
    }

    public Student deleteStudent(int studentRollNo){

        //safely find the student;
        Student student = studentRepo.findById(studentRollNo).orElseThrow(()-> new RuntimeException("Student not found"));

        //delete the found student;
        studentRepo.delete(student);

        return student;
    }
}
