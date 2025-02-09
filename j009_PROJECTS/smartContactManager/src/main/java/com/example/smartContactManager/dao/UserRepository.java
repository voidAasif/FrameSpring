package com.example.smartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.smartContactManager.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
