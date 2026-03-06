package com.divyansh.studentmanagementsystem.project.repository;

import com.divyansh.studentmanagementsystem.project.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}