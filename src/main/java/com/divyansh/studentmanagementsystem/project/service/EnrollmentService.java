package com.divyansh.studentmanagementsystem.project.service;


import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.EnrollmentResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentSimpleResponseDTO;

import java.util.List;


public interface EnrollmentService {

    EnrollmentResponseDTO enrollStudent(Long studentId, Long courseId);

    List<CourseResponseDTO> getAllCoursesOfStudent(Long studentId);

    List<StudentSimpleResponseDTO> getAllStudentsOfCourse(Long courseId);

    List<EnrollmentResponseDTO> getAllEnrollments();

    EnrollmentResponseDTO getEnrollmentById(Long enrollmentId);
}
