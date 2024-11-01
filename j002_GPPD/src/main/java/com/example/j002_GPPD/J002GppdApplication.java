package com.example.j002_GPPD;

/*
 * G - get
 * P - put
 * P - post
 * D - delete
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class J002GppdApplication {

	public static void main(String[] args) {
		SpringApplication.run(J002GppdApplication.class, args);

		System.out.println("App load successfully"); //log;
	}

}
