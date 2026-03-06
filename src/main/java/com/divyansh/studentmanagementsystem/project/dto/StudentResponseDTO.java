package com.divyansh.studentmanagementsystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDTO {

    private long id;

    private String name;

    private int age;

    private long phone;

    private String email;

    List<CourseResponseDTO> courses;
}
