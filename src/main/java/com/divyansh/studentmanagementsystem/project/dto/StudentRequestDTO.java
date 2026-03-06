package com.divyansh.studentmanagementsystem.project.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDTO {

    @NotBlank(message = "name is required")
    @Size(min = 3 , max = 20, message = "size should be between 3 or 20")
    private String name;

    @Min(5)
    private int age;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
    private String phone;

    @Email
    @NotBlank(message = "email is required")
    private String email;
}
