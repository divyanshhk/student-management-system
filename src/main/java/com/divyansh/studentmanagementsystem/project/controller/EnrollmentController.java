package com.divyansh.studentmanagementsystem.project.controller;

import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.EnrollmentResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentSimpleResponseDTO;
import com.divyansh.studentmanagementsystem.project.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/student/{studentId}/courses/{courseId}")
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent (
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, courseId));
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(enrollmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseResponseDTO>> getAllCoursesOfStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getAllCoursesOfStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentSimpleResponseDTO>> getAllStudentsOfCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getAllStudentsOfCourse(courseId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }
}
