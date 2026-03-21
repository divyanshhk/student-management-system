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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<CourseResponseDTO> getAllCoursesOfStudent(Long studentId, Pageable pageable) {
        Page<Enrollment> enrollments  = enrollmentRepository.findByStudentId(studentId, pageable);

        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("No enrollment found for studentId : " + studentId);
        }

        return enrollments
                .map(enrollment -> modelMapper.map(enrollment.getCourse(), CourseResponseDTO.class));

    }

    @Override
    public Page<StudentSimpleResponseDTO> getAllStudentsOfCourse(Long courseId, Pageable pageable) {

        Page<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId, pageable);

        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("No enrollment found for courseId : " + courseId);
        }
        return enrollments
                .map(enrollment -> modelMapper.map(enrollment.getStudent(), StudentSimpleResponseDTO.class));

    }

    @Override
    public Page<EnrollmentResponseDTO> getAllEnrollments(Pageable pageable) {

        Page<Enrollment> enrollments =  enrollmentRepository.findAll(pageable);

        if (enrollments.isEmpty()) {
            throw new ResourceNotFoundException("No enrollment found");
        }
        return enrollments
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentResponseDTO.class));
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return modelMapper.map(enrollment, EnrollmentResponseDTO.class);
    }

    @Override
    public void deleteEnrollmentById(Long enrollmentId) {
        if(!enrollmentRepository.existsById(enrollmentId)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        enrollmentRepository.deleteById(enrollmentId);
    }

}
