package com.divyansh.studentmanagementsystem.project.service;


import com.divyansh.studentmanagementsystem.project.dto.StudentRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    public StudentResponseDTO getStudentById(long id);

    public Page<StudentResponseDTO> getAllStudent(int page, int size);

    public StudentResponseDTO updateStudent(long id, StudentRequestDTO studentRequestDTO);

    public void deleteStudentById(long id);
}
