package com.divyansh.studentmanagementsystem.project.service;


import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.EnrollmentResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentSimpleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EnrollmentService {

    EnrollmentResponseDTO enrollStudent(Long studentId, Long courseId);

    Page<CourseResponseDTO> getAllCoursesOfStudent(Long studentId, Pageable pageable);

    Page<StudentSimpleResponseDTO> getAllStudentsOfCourse(Long courseId, Pageable pageable);

    Page<EnrollmentResponseDTO> getAllEnrollments(Pageable pageable);

    EnrollmentResponseDTO getEnrollmentById(Long enrollmentId);

    void deleteEnrollmentById(Long enrollmentId);
}
