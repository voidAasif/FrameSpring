package com.example.j008_serverSideValidation.entity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserData {

    //these annotations comes form Hibernate Validation;
    @NotBlank(message = "Name can't be empty !!") //make sure the value of this attribute is not empty;
    @Size(min = 3, max = 7, message = "Enter userName size between 3 to 7 char !!") //if length of attribute value is not between given range it throw this message;
    private String userName;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !!") //regular expression to check mail formate;
    private String userEmail;

    @AssertTrue(message = "check it first !!") //throw this message if the value of this attribute is false;
    private boolean terms;

    
    public UserData() {}

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public boolean getTerms() {
        return terms;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }
    
    @Override
    public String toString() {
        return "UserData [userName=" + userName + ", userEmail=" + userEmail + ", terms=" + terms + "]";
    }

    
}
