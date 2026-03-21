package com.divyansh.studentmanagementsystem.project.repository;

import com.divyansh.studentmanagementsystem.project.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByNameContaining(String name, Pageable pageable);
}