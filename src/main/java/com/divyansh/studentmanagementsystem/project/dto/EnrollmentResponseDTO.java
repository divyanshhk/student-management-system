package com.divyansh.studentmanagementsystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDTO {

    private Long enrollmentId;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseTitle;
}
