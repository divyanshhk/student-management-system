package com.divyansh.studentmanagementsystem.project.controller;

import com.divyansh.studentmanagementsystem.project.dto.PaginationResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.response.ApiResponse;
import com.divyansh.studentmanagementsystem.project.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @RequestMapping("/profile")
    public String studentProfile() {
        return "Student Profile";
    }

    @Operation(summary = "Add new student")
    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO student = studentService.createStudent(studentRequestDTO);

        ApiResponse<StudentResponseDTO> response = new ApiResponse<>(true, "student created successfully", student);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> getStudentById(@PathVariable long id){

        StudentResponseDTO student = studentService.getStudentById(id);

        ApiResponse<StudentResponseDTO> response = new ApiResponse<>(true, "student fetched successfully", student);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponseDTO<StudentResponseDTO>>> getAllStudent(Pageable pageable) {

        Page<StudentResponseDTO> students = studentService.getAllStudent(pageable);

        PaginationResponseDTO<StudentResponseDTO> pagination = new PaginationResponseDTO<>(students);

        ApiResponse<PaginationResponseDTO<StudentResponseDTO>> response = new ApiResponse<>(true, "students fetched successfully", pagination);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> updateStudent(@PathVariable long id, @RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO student = studentService.updateStudent(id, studentRequestDTO);

        ApiResponse<StudentResponseDTO> response = new ApiResponse<>(true, "student updated successfully", student);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteStudent(@PathVariable long id) {

        studentService.deleteStudentById(id);

        ApiResponse<Object> response = new ApiResponse<>(true, "student deleted successfully", id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Search student by title")
    public ResponseEntity<ApiResponse<PaginationResponseDTO<StudentResponseDTO>>> searchStudent(@RequestParam String title , Pageable pageable ) {

        Page<StudentResponseDTO> students = studentService.searchStudentByName(title, pageable);

        PaginationResponseDTO<StudentResponseDTO> pagination = new PaginationResponseDTO<>(students);

        ApiResponse<PaginationResponseDTO<StudentResponseDTO>> response = new ApiResponse<>(true, "student fetched successfully", pagination);

        return ResponseEntity.ok(response);
    }
}
