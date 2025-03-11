package com.example.smartContactManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smartContactManager.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    //pageable has two values: 1. currentPage, 2. contact per page(in my case: 5);
    public Page<Contact> findByUserUserId(int userId, Pageable pageable);

    public Contact findByContactId(int contactId);
}
