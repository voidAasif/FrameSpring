package com.example.j005_ERMapping.subjects.entity;

// import java.util.Objects;

public class Subject {
    private int subjectCode;
    private String subjectName;
    private boolean subjectComplete;

    public Subject(){}

    public Subject(int subjectCode, String subjectName, boolean subjectComplete){
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectComplete = subjectComplete;
    }

    public void setSubCode(int subjectCode){
        this.subjectCode = subjectCode;
    }

    public void setSubName(String subjectName){
        this.subjectName = subjectName;
    }

    public void setSubComplete(boolean subjectComplete){
        this.subjectComplete = subjectComplete;
    }

    public int getSubCode(){
        return subjectCode;
    }

    public String getSubName(){
        return subjectName;
    }

    public boolean getSubComplete(){
        return subjectComplete;
    }

    @Override
    public String toString(){
        return "\nSubject Code: " + subjectCode + ", \nSubject Name: " + subjectName + ", \nSubject Complete: " + subjectComplete + "\n";
    }

    @Override
    public boolean equals(Object obj){
        Subject that = (Subject) obj;
        if(this.getSubCode() == that.getSubCode()) return true; //diff subject on the basic of its subject code;
        return this == that;
    }

    // @Override
    // public int hashCode(){
    //     return Objects.hash(subjectCode);
    // }
}
