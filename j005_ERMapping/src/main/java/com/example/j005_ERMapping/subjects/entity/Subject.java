package com.example.j005_ERMapping.subjects.entity;

import com.example.j005_ERMapping.students.entity.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

// import java.util.Objects;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectCode;
    private String subjectName;
    private boolean subjectComplete;

    @OneToOne(mappedBy = "subject") //now it not make new column of studentId into subject table for Foreign key | "subject" -> student class;
    @JsonBackReference //now it break the loop between student and subject; //for JSON child;
    private Student student;

    public Subject(){}

    public Subject(int subjectCode, String subjectName, boolean subjectComplete){
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectComplete = subjectComplete;
    }

    public void setSubjectCode(int subjectCode){
        this.subjectCode = subjectCode;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }

    public void setSubjectComplete(boolean subjectComplete){
        this.subjectComplete = subjectComplete;
    }

    public int getSubjectCode(){
        return subjectCode;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public boolean getSubjectComplete(){
        return subjectComplete;
    }

    @Override
    public String toString(){
        return "\nSubject Code: " + subjectCode + ", \nSubject Name: " + subjectName + ", \nSubject Complete: " + subjectComplete + "\n";
    }

    @Override
    public boolean equals(Object obj){
        Subject that = (Subject) obj;
        if(this.getSubjectCode() == that.getSubjectCode()) return true; //diff subject on the basic of its subject code;
        return this == that;
    }

    // @Override
    // public int hashCode(){
    //     return Objects.hash(subjectCode);
    // }
}
