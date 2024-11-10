package com.example.j003_DBase.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.j003_DBase.entity.Student;

@Service //add this class into java bean;
public class StudentServices {

    public List<Student> seeData(){
        System.out.println("seeData service work");
        return List.of( new Student("Aasif", true),
                        new Student("Shakir", false));
    }

    public void createData(@RequestBody Student myStudent){

    }

    public void updateData(@PathVariable int stuId, @RequestBody Student myStudent){
        
    }

    public void deleteMapping(@PathVariable int stuId, @RequestBody Student myStudent){

    }
}
