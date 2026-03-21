package com.divyansh.studentmanagementsystem.project.implementation;

import com.divyansh.studentmanagementsystem.project.dto.CourseRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.Course;
import com.divyansh.studentmanagementsystem.project.entity.Lecturer;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import com.divyansh.studentmanagementsystem.project.repository.CourseRepository;
import com.divyansh.studentmanagementsystem.project.repository.LecturerRepository;
import com.divyansh.studentmanagementsystem.project.service.CourseService;
import com.divyansh.studentmanagementsystem.project.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        Course course = modelMapper.map(courseRequestDTO, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseResponseDTO.class);

    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found exception "));
        return modelMapper.map(course, CourseResponseDTO.class);
    }

    @Override
    public Page<CourseResponseDTO> getAllCourses(Pageable pageable) {

        Page<Course> courses = courseRepository.findAll(pageable);

        return courses.map(course -> modelMapper.map(course, CourseResponseDTO.class));
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found "));
        modelMapper.map(courseRequestDTO, course);
        Course updatedCourse = courseRepository.save(course);
        return modelMapper.map(course, CourseResponseDTO.class);
    }

    @Override
    public void deleteCourseById(Long id) {
        if(!courseRepository.existsById(id)) {
           throw new ResourceNotFoundException("Resource not found");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDTO assignLecturer(Long courseId, Long lecturerId) {

        Course course  = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found "));

        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        course.setLecturer(lecturer);
        courseRepository.save(course);
        return modelMapper.map(course, CourseResponseDTO.class);

    }

    @Override
    public Page<CourseResponseDTO> searchCourseByName(String title, Pageable pageable) {
        Page<Course> courses = courseRepository.findByTitleContaining(title, pageable);
        return courses.map(course -> modelMapper.map(course, CourseResponseDTO.class));
    }


}
