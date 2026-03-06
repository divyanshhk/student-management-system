package com.divyansh.studentmanagementsystem.project.implementation;

import com.divyansh.studentmanagementsystem.project.dto.CourseRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.Course;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import com.divyansh.studentmanagementsystem.project.repository.CourseRepository;
import com.divyansh.studentmanagementsystem.project.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
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
    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());
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


}
