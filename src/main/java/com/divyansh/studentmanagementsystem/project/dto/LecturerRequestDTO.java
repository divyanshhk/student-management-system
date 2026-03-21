package com.divyansh.studentmanagementsystem.project.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LecturerRequestDTO {

    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "email is required")
    private String email;

    private String department;



}
