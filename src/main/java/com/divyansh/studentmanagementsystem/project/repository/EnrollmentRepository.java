package com.divyansh.studentmanagementsystem.project.repository;

import com.divyansh.studentmanagementsystem.project.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);

    Page<Enrollment> findByCourseId(Long courseId, Pageable pageable);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}