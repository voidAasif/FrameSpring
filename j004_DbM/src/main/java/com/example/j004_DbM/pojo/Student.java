package com.example.j004_DbM.pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "StudentDbM")
public class Student {
    
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    private String studentName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    public Student(){}
    public Student(String studentName, Date dob){
        this.studentName = studentName;
        this.dob = dob;
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public void setDob(Date dob){
        this.dob = dob;
    }

    public int getStudentId(){
        return studentId;
    }
    public String getStudentName(){
        return studentName;
    }
    public Date getDob(){
        return dob;
    }

    @Override
    public String toString(){
        return String.format("[\n\sStudentId: %d,\n\sStudentName: %s,\n\sStudentDob: %s\n],", studentId, studentName, dob);
    }

}