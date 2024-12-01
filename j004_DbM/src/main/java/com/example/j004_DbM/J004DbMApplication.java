package com.example.j004_DbM;

// import java.sql.Date;
// import java.time.LocalDate;
// import java.util.function.Consumer;
import java.util.List;
import com.example.j004_DbM.pojo.Student;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.j004_DbM.repo.StudentRepo;

@SpringBootApplication
public class J004DbMApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(J004DbMApplication.class, args);
		StudentRepo studentRepo = context.getBean(StudentRepo.class);
		
//#0	//empty table;
		// studentRepo.deleteAll();
		
//#1	//save method;
		// Student st1 = new Student("someone", Date.valueOf(LocalDate.of(2000, 01, 01)));
		// System.out.println(studentRepo.save(st1)); //use to save single entity, return entity;

//#2	//saveAll method;
		// List<Student> studentList = List.of(
		// 	new Student("student1", Date.valueOf(LocalDate.of(2001, 01, 1))),
		// 	new Student("student2", Date.valueOf(LocalDate.of(2002, 02, 2))),
		// 	new Student("student3", Date.valueOf(LocalDate.of(2003, 03, 3))),
		// 	new Student("student4", Date.valueOf(LocalDate.of(2004, 04, 4))),
		// 	new Student("student5", Date.valueOf(LocalDate.of(2005, 05, 5)))
		// );
		// Iterable<Student> studentIterable = studentRepo.saveAll(studentList); //save list of entity, return iterable;
		// studentIterable.forEach(student-> System.out.println(student)); //iterate using lambda;

//#3	//findById method;
		// System.out.println(studentRepo.findById(4)); //return optional;

//#4	//findAll method;
		// Iterable<Student> students = studentRepo.findAll();

		// students.forEach(new Consumer<Student>(){ //iterate using abstract consumer class;
		// 	@Override
		// 	public void accept(Student t) {
		// 		System.out.println(t);
		// 	}
			
		// });

//#5	//findAllById method;
		// List<Integer> ids = List.of(2, 3, 6);
		// Iterable<Student> students = studentRepo.findAllById(ids); //it takes iterables, return list of entity;
		// students.forEach(student -> System.out.println(student));


//#6	//use custom finder methods;

		//find by student name;
		// List<Student> student = studentRepo.findByStudentName("student3");
		// System.out.println(student);

		//find by student dob;
		// List<Student> student = studentRepo.findByDob(Date.valueOf(LocalDate.of(2003, 12, 8)));
		// System.out.println(student);

		//find by student name and dob;
		// List<Student> student = studentRepo.findByStudentNameAndDob("student3", Date.valueOf(LocalDate.of(2003, 3, 3)));
		// System.out.println(student);

//#7	//Custom queries;

		//It execute JPQL;
		// Student student = studentRepo.giveStudentByName("student2");
		// System.out.println(student);

		//It execute Native query;
		// List<Student> student = studentRepo.giveAllStudent();
		// System.out.println(student);

		System.out.println("Done");
	}
}
