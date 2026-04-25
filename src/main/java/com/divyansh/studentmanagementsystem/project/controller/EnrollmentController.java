package com.divyansh.studentmanagementsystem.project.controller;

import com.divyansh.studentmanagementsystem.project.dto.*;
import com.divyansh.studentmanagementsystem.project.response.ApiResponse;
import com.divyansh.studentmanagementsystem.project.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> enrollStudent (
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        EnrollmentResponseDTO enrollment = enrollmentService.enrollStudent(studentId, courseId);

        ApiResponse<EnrollmentResponseDTO> response = new ApiResponse<>(true, "student enrolled successfully", enrollment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<ApiResponse<EnrollmentResponseDTO>> getEnrollmentById(@PathVariable Long enrollmentId) {

        EnrollmentResponseDTO enrollment = enrollmentService.getEnrollmentById(enrollmentId);

        ApiResponse<EnrollmentResponseDTO> response = new ApiResponse<>(true, "enrollment fetched successfully", enrollment);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<PaginationResponseDTO<CourseResponseDTO>>> getAllCoursesOfStudent(@PathVariable Long studentId, Pageable pageable) {

        Page<CourseResponseDTO> courses = enrollmentService.getAllCoursesOfStudent(studentId, pageable);

        PaginationResponseDTO<CourseResponseDTO> pagination = new PaginationResponseDTO<>(courses);

        ApiResponse<PaginationResponseDTO<CourseResponseDTO>> response = new ApiResponse<>(true, "courses fetched successfully", pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<PaginationResponseDTO<StudentSimpleResponseDTO>>> getAllStudentsOfCourse(@PathVariable Long courseId, Pageable pageable) {

        Page<StudentSimpleResponseDTO> students = enrollmentService.getAllStudentsOfCourse(courseId, pageable);

        PaginationResponseDTO<StudentSimpleResponseDTO> pagination = new PaginationResponseDTO<>(students);

        ApiResponse<PaginationResponseDTO<StudentSimpleResponseDTO>> response = new ApiResponse<>(true, "students fetched successfully", pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponseDTO<EnrollmentResponseDTO>>> getAllEnrollments(Pageable pageable) {

        Page<EnrollmentResponseDTO> enrollments = enrollmentService.getAllEnrollments(pageable);

        PaginationResponseDTO<EnrollmentResponseDTO> pagination = new PaginationResponseDTO<>(enrollments);

        ApiResponse<PaginationResponseDTO<EnrollmentResponseDTO>> response = new ApiResponse<>(true, "enrollments fetched successfully", pagination);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{enrollment}")
    public ResponseEntity<ApiResponse<Object>> deleteEnrollmentById(@PathVariable Long enrollmentId) {

        enrollmentService.deleteEnrollmentById(enrollmentId);

        ApiResponse<Object> response = new ApiResponse<>(true, "enrollment deleted successfully",  enrollmentId);

        return ResponseEntity.ok(response);

    }
}
