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

    @Min(value = 5, message = "Age must be greater than 5")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
    private String phone;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}
