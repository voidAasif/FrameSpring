package com.example.j002_GPPD.entity;

public class Student {

    private int studentId;
    private String studentName;
    private boolean StudentAttend;

    //setters;
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public void setStudentAttend(boolean StudentAttend){
        this.StudentAttend = StudentAttend;
    }

    //getters;
    public int getStudentId(){
        return studentId;
    }
    public String getStudentName(){
        return studentName;
    }
    public boolean getStudentAttend(){
        return StudentAttend;
    }
}

//JSON
/*

{
    "studentId": 12,
    "studentName": "aasif",
    "studentAttend": true
}
    
 */
