package com.divyansh.studentmanagementsystem.project.controller;

import com.divyansh.studentmanagementsystem.project.dto.StudentRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Create new student")
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<StudentResponseDTO>> getAllStudent(
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(studentService.getAllStudent(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable long id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentRequestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}
