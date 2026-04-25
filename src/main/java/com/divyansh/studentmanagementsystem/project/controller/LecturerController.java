package com.divyansh.studentmanagementsystem.project.controller;

import com.divyansh.studentmanagementsystem.project.dto.*;
import com.divyansh.studentmanagementsystem.project.response.ApiResponse;
import com.divyansh.studentmanagementsystem.project.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecturers")
@RequiredArgsConstructor
public class LecturerController {

    private final LecturerService lecturerService;
    private final CourseService courseService;

    @Operation(summary = "Add new lecturer")
    @PostMapping
    public ResponseEntity <ApiResponse<LecturerResponseDTO>> createLecturer(@RequestBody LecturerRequestDTO lecturerRequestDTO) {

        LecturerResponseDTO lecturer = lecturerService.createLecturer(lecturerRequestDTO);

        ApiResponse<LecturerResponseDTO> response = new ApiResponse<>(true, "lecturer created successfully", lecturer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/courses/{courseId}/lecturer/{lecturerId}")
    public ResponseEntity <ApiResponse<CourseResponseDTO>> assignLecturer(
            @PathVariable Long courseId,
            @PathVariable Long lecturerId
    ){
        CourseResponseDTO course = courseService.assignLecturer(courseId, lecturerId);

        ApiResponse<CourseResponseDTO> response = new ApiResponse<>(true, "course assigned successfully", course);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<ApiResponse<LecturerResponseDTO>> getLecturerById(@PathVariable Long lecturerId){

        LecturerResponseDTO lecturer = lecturerService.getLecturerById(lecturerId);

        ApiResponse<LecturerResponseDTO> response = new ApiResponse<>(true, "lecturer fetched successfully", lecturer);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponseDTO<LecturerResponseDTO>>> getAllLecturer(Pageable pageable){

        Page<LecturerResponseDTO> lecturers = lecturerService.getAllLecturer(pageable);

        PaginationResponseDTO<LecturerResponseDTO> pagination = new PaginationResponseDTO<>(lecturers);

        ApiResponse<PaginationResponseDTO<LecturerResponseDTO>> response = new ApiResponse<>(true, "pagination successfully", pagination);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{lecturerId}")
    public ResponseEntity<ApiResponse<LecturerResponseDTO>> updateLecturer(@PathVariable Long lecturerId, @RequestBody LecturerRequestDTO lecturerRequestDTO){

        LecturerResponseDTO lecturer = lecturerService.updateLecturer(lecturerId, lecturerRequestDTO);

        ApiResponse<LecturerResponseDTO>  response = new ApiResponse<>(true, "lecturer updated successfully", lecturer);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{lecturerId}")
    public ResponseEntity<ApiResponse<Object>> deleteLecturer(@PathVariable Long lecturerId){

        lecturerService.deleteLecturer(lecturerId);

        ApiResponse<Object> response = new ApiResponse<>(true, "lecturer deleted successfully", lecturerId);

        return ResponseEntity.ok(response);
    }
}
