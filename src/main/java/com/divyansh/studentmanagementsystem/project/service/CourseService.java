package com.divyansh.studentmanagementsystem.project.service;

import com.divyansh.studentmanagementsystem.project.dto.CourseRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);

    CourseResponseDTO getCourseById(Long id);

    List<CourseResponseDTO> getAllCourses();

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO);

    void deleteCourseById(Long id);
}
