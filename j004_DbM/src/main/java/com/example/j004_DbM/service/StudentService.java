package com.example.j004_DbM.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.j004_DbM.pojo.Student;
import com.example.j004_DbM.repo.StudentRepo;

@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    // public StudentService(StudentRepo studentRepo){
    //     this.studentRepo = studentRepo;
    // }
    
    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentByName(String studentName){
        return studentRepo.giveStudentByName(studentName);
    }

    public Student addStudent(Student myStudent){
        return studentRepo.save(myStudent);
    }

    public Student updateStudent(int studentId, Student myStudent){
        Student existStudent = studentRepo.findById(studentId).orElseThrow(()-> new RuntimeException("Student not found"));
        
        existStudent.setStudentName(myStudent.getStudentName());
        existStudent.setDob(myStudent.getDob());

        return studentRepo.save(existStudent);
    }

    public boolean deleteStudent(int studentId){
        Optional<Student> student = studentRepo.findById(studentId);
        if(student.isPresent()){
            studentRepo.deleteById(studentId);
            return true;
        } 
        return false;
    }
}
