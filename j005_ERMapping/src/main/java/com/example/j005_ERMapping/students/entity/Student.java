package com.example.j005_ERMapping.students.entity;

// import java.util.Objects;

import com.example.j005_ERMapping.subjects.entity.Subject;

public class Student {
    private int rollNo;
    private String name;
    private char grade;
    private Subject subject;  // mapping with another table or class, one-to-one mapping;

    public Student(){}

    public Student(int rollNo, String name, char grade, Subject subject){
        this.rollNo = rollNo;
        this.name = name;
        this.grade = grade;
        this.subject = subject;
    }

    public void setRollNo(int rollNo){
        this.rollNo = rollNo;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGrade(char grade){
        this.grade = grade;
    }

    public void setSubject(Subject subject){
        this.subject = subject;
    }

    public int getRollNo(){
        return rollNo;
    }

    public String getName(){
        return name;
    }

    public char getGrade(){
        return grade;
    }

    public Subject getSubject(){
        return subject;
    }

    @Override
    public String toString(){
        return "\nRollNo: " + rollNo + ", \nName: " + name + ", \nGrade: " + grade + ", \nSubject: " + subject + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        Student that = (Student) obj;
        if(this.getRollNo() == that.getRollNo()) return true; //diff student on the basis of its rollNo;
        return this == that;
    }

    // @Override
    // public int hashCode(){
    //     return Objects.hash(rollNo);
    // }
}
