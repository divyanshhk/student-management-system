package com.divyansh.studentmanagementsystem.project.implementation;

import com.divyansh.studentmanagementsystem.project.dto.CourseResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.EnrollmentResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentSimpleResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.Course;
import com.divyansh.studentmanagementsystem.project.entity.Enrollment;
import com.divyansh.studentmanagementsystem.project.entity.Student;
import com.divyansh.studentmanagementsystem.project.error.exception.DuplicateEnrollmentException;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import com.divyansh.studentmanagementsystem.project.repository.CourseRepository;
import com.divyansh.studentmanagementsystem.project.repository.EnrollmentRepository;
import com.divyansh.studentmanagementsystem.project.repository.StudentRepository;
import com.divyansh.studentmanagementsystem.project.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    @Override
    public EnrollmentResponseDTO enrollStudent(Long studentId, Long courseId) {

        boolean exists = enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
        if (exists) {
            throw new DuplicateEnrollmentException("Student already enrolled in this course");
        }

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id : " + studentId));

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with id : " + courseId));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return EnrollmentResponseDTO.builder()
                .enrollmentId(savedEnrollment.getId())
                .studentId(student.getId())
                .studentName(student.getName())
                .courseId(course.getId())
                .courseTitle(course.getTitle())
                .build();
    }

    @Override
    public List<CourseResponseDTO> getAllCoursesOfStudent(Long studentId) {
        List<Enrollment> enrollments  = enrollmentRepository.findByStudentId(studentId);

        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("No enrollment found for studentId : " + studentId);
        }

        return enrollments.stream()
                .map(e -> modelMapper.map(e.getCourse(), CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentSimpleResponseDTO> getAllStudentsOfCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("No enrollment found for courseId : " + courseId);
        }
        return enrollments
                .stream()
                .map(e -> modelMapper.map(e.getStudent(), StudentSimpleResponseDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return modelMapper.map(enrollment, EnrollmentResponseDTO.class);
    }


}
