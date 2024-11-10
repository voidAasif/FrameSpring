package com.example.j003_DBase.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.j003_DBase.entity.Student;

// this interface is only used because of it has all methods of sqlOperations;
public interface StudentRepo extends JpaRepository<Student, Integer> { }
