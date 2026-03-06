package com.divyansh.studentmanagementsystem.project.repository;

import com.divyansh.studentmanagementsystem.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}