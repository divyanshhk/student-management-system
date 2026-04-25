package com.divyansh.studentmanagementsystem.project.controller;


import com.divyansh.studentmanagementsystem.project.dto.CourseRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.PaginationResponseDTO;
import com.divyansh.studentmanagementsystem.project.response.ApiResponse;
import com.divyansh.studentmanagementsystem.project.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    public final CourseService courseService;

    @PostMapping
    @Operation(summary = "Add new Course")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {

        CourseResponseDTO course = courseService.createCourse(courseRequestDTO);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(true, "Course Created", course);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

   @GetMapping("/{id}")
   public ResponseEntity<ApiResponse<CourseResponseDTO>> getCourseById(@PathVariable Long id) {

        CourseResponseDTO course = courseService.getCourseById(id);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(true, "Course Fetched", course);

        return ResponseEntity.status(HttpStatus.OK).body(response);
   }

   @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponseDTO<CourseResponseDTO>>> getAllCourses(Pageable pageable) {

        Page<CourseResponseDTO> courses = courseService.getAllCourses(pageable);

        PaginationResponseDTO<CourseResponseDTO> pagination = new PaginationResponseDTO<>(courses);

        ApiResponse<PaginationResponseDTO<CourseResponseDTO>>  response = new ApiResponse<>(true, "Course Fetched", pagination);

        return ResponseEntity.ok(response);
   }

   @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDTO>> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO courseRequestDTO) {

        CourseResponseDTO course = courseService.updateCourse(id, courseRequestDTO);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(true, "Course Updated", course);

        return ResponseEntity.ok(response);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCourse(@PathVariable Long id) {

       courseService.deleteCourseById(id);

       ApiResponse<Object> response = new ApiResponse<>(true, "Course Deleted", id);

       return ResponseEntity.ok(response);
   }

   @GetMapping("/search")
   @Operation(summary = "Search course by title")
   public ResponseEntity <ApiResponse<PaginationResponseDTO<CourseResponseDTO>>> searchCourse(@RequestParam String title, Pageable pageable) {

        Page<CourseResponseDTO> courses = courseService.searchCourseByName(title, pageable);

        PaginationResponseDTO<CourseResponseDTO> pagination = new PaginationResponseDTO<>(courses);

        ApiResponse<PaginationResponseDTO<CourseResponseDTO>> response = new ApiResponse<>(true, "course fetched successfully", pagination);
        return  ResponseEntity.ok(response);
   }
}
