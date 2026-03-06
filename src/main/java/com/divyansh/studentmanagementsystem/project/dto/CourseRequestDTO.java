package com.divyansh.studentmanagementsystem.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDTO {

    @NotBlank(message = "Course title is required")
    @Size(min = 3, max = 50, message = "Course title must be between 3 to 50 character")
    private String title;

    @NotBlank(message = "Course description is required")
    @Size(min = 10, max = 300, message = "Description must be between 10 to 300 characters")
    private String description;
}
