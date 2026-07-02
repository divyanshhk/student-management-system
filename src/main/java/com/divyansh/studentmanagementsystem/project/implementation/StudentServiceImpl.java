package com.divyansh.studentmanagementsystem.project.implementation;

import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.Enrollment;
import com.divyansh.studentmanagementsystem.project.entity.Student;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import com.divyansh.studentmanagementsystem.project.repository.StudentRepository;
import com.divyansh.studentmanagementsystem.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    // Builds the response DTO explicitly instead of relying on ModelMapper's
    // implicit matching to guess how to fill "courses" from the entity graph.
    private StudentResponseDTO toStudentResponseDTO(Student student) {
        List<CourseResponseDTO> courses = student.getEnrollments() == null
                ? Collections.emptyList()
                : student.getEnrollments().stream()
                .map(Enrollment::getCourse)
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());

        StudentResponseDTO dto = modelMapper.map(student, StudentResponseDTO.class);
        dto.setCourses(courses);
        return dto;
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return toStudentResponseDTO(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return toStudentResponseDTO(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentResponseDTO> getAllStudent(Pageable pageable) {
//        Sort sort = direction.equalsIgnoreCase("desc") ?
//                Sort.by(sortBy).descending():
//                Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Student> students = studentRepository.findAll(pageable);

        return students.map(this::toStudentResponseDTO);

    }

    @Override
    public StudentResponseDTO updateStudent(long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        modelMapper.map(studentRequestDTO, student);
        Student updatedStudent = studentRepository.save(student);

        return toStudentResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudentById(long id) {
        if(!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentResponseDTO> searchStudentByName(String title, Pageable pageable) {
        Page<Student> students = studentRepository.findByNameContaining(title, pageable);
        return students.map (this::toStudentResponseDTO);
    }


}
