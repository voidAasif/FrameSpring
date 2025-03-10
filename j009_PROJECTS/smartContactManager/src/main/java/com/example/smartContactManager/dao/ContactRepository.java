package com.example.smartContactManager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartContactManager.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    public List<Contact> findByUserUserId(int userId);
}
