package com.divyansh.studentmanagementsystem.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication {


	public static void main(String[] args) {
		System.out.println("DB_URL = " + System.getenv("DB_URL"));

		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

}
