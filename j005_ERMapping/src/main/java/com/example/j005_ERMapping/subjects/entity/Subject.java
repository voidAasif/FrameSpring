package com.example.j005_ERMapping.subjects.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// import java.util.Objects;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectCode;
    private String subjectName;
    private boolean subjectComplete;

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
