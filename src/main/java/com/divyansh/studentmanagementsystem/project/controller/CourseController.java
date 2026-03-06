package com.divyansh.studentmanagementsystem.project.controller;


import com.divyansh.studentmanagementsystem.project.dto.CourseRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    public final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequestDTO));
    }

   @GetMapping("/{id}")
   public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
   }

   @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
   }

   @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequestDTO));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
       courseService.deleteCourseById(id);
       return ResponseEntity.noContent().build();
   }
}
