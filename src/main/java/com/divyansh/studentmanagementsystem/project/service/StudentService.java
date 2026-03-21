package com.divyansh.studentmanagementsystem.project.service;


import com.divyansh.studentmanagementsystem.project.dto.StudentRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    public StudentResponseDTO getStudentById(long id);

    public Page<StudentResponseDTO> getAllStudent(Pageable pageable);

    public StudentResponseDTO updateStudent(long id, StudentRequestDTO studentRequestDTO);

    public void deleteStudentById(long id);

    public Page<StudentResponseDTO> searchStudentByName(String title, Pageable pageable);
}
