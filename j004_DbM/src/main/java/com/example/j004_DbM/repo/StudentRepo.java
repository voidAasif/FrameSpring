package com.example.j004_DbM.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.j004_DbM.pojo.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    // custom finders methods;
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    public List<Student> findByStudentName(String studentName); //here find -> introducer and Name -> criteria;
    public List<Student> findByDob(Date dob);
    public List<Student> findByStudentNameAndDob(String studentName, Date dob);

    //CUstom queries;
    //JPQL - Hibernate QL;
    //Native QL - SQL;

    @Query("SELECT s FROM Student s WHERE s.studentName =:n") //JPQL;
    public Student giveStudentByName(@Param("n") String name);

    @Query(value = "SELECT * FROM student_dbm", nativeQuery = true) //native query;
    public List<Student> giveAllStudent();
}
