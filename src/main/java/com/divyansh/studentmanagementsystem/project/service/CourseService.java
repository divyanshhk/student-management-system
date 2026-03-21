package com.divyansh.studentmanagementsystem.project.service;

import com.divyansh.studentmanagementsystem.project.dto.CourseRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);

    CourseResponseDTO getCourseById(Long id);

    Page<CourseResponseDTO> getAllCourses(Pageable pageable);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO);

    void deleteCourseById(Long id);

    CourseResponseDTO assignLecturer(Long courseId, Long lecturerId);

    Page<CourseResponseDTO> searchCourseByName(String title, Pageable pageable);
}
