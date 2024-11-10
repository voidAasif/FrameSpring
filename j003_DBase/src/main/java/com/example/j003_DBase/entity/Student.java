package com.example.j003_DBase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //hibernate;
@Table(name = "student") //db;
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer studentId;

    private String studentName;
    private boolean studentAttend;

    // Default constructor is necessary for JPA
    public Student() {
    }

    // Parameterized constructor
    public Student(String studentName, boolean studentAttend) {
        this.studentName = studentName;
        this.studentAttend = studentAttend;
    }

    // Setters
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentAttend(boolean studentAttend) {
        this.studentAttend = studentAttend;
    }

    // Getters
    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public boolean isStudentAttend() {
        return studentAttend;
    }
}
