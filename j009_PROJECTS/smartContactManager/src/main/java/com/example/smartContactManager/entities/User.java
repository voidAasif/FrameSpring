package com.example.smartContactManager.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(length = 100)
    private String about;

    private String role;

    @Column(nullable = false, columnDefinition = "TINYINT(1)") //by default it set this column dataType as bit which arise problem in mariaDB so i manually set it as tinyint;
    private boolean enabled;
    
    private String image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") //mapped by user map these all contacts with some user;
    private List<Contact> contacts = new ArrayList<>(); //store multiple contacts of single user;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
                + about + ", role=" + role + ", enabled=" + enabled + ", image=" + image + ", contacts=" + contacts
                + "]";
    }

    

}

/*
    @OneToMany Annotation: Specifies a one-to-many relationship where one User can have many Contact entities.
    cascade = CascadeType.ALL: Any operation (like persist, delete, etc.) performed on the User entity will also be propagated to its associated Contact entities.
    mappedBy = "user": Indicates that the mapping is defined in the Contact entity through the user field.
*/