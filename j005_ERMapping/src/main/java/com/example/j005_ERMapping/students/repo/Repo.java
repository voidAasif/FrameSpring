package com.example.j005_ERMapping.students.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.j005_ERMapping.students.entity.Student;

@Repository
public interface Repo extends JpaRepository<Student, Integer> {
    
}
