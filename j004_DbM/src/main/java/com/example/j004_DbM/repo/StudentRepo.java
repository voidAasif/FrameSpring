package com.example.j004_DbM.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.j004_DbM.pojo.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {}
