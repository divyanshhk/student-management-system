package com.divyansh.studentmanagementsystem.project.service;

import com.divyansh.studentmanagementsystem.project.dto.LecturerRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.LecturerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LecturerService {

    LecturerResponseDTO createLecturer(LecturerRequestDTO lecturerRequestDTO);

    LecturerResponseDTO getLecturerById(Long lecturerId);

    Page<LecturerResponseDTO> getAllLecturer(Pageable pageable);

    LecturerResponseDTO updateLecturer(Long lecturerId, LecturerRequestDTO lecturerRequestDTO);

    void deleteLecturer(Long lecturerId);
}
