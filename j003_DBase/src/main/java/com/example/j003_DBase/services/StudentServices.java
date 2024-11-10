package com.example.j003_DBase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.j003_DBase.entity.Student;
import com.example.j003_DBase.repo.StudentRepo;

@Service //add this class into java bean;
public class StudentServices {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServices(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    public List<Student> seeData(){
        System.out.println("seeData service work");
        return studentRepo.findAll(); //this method comes form JpaRepository;
    }

    public void addData(Student myStudent){
        studentRepo.save(myStudent);
    }

    public void updateData(int stuId,Student myStudent){

        //find student into db and create copy myStudent in existingStudent;
        Student existingStudent = studentRepo.findById(stuId).orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setStudentName(myStudent.getStudentName()); // get name and attend of myStudent and insert into existingStudent;
        existingStudent.setStudentAttend(myStudent.isStudentAttend());
        // set other properties from myStudent to existingStudent

        studentRepo.save(existingStudent); // this will update the existing record;
    }

    public void deleteData(int stuId){
        studentRepo.deleteById(stuId); //delete student by id;
    }
}
