package com.divyansh.studentmanagementsystem.project.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {

    private Long id;

    private String title;

    private String description;

}
