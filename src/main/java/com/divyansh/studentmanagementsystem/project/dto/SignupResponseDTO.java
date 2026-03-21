package com.divyansh.studentmanagementsystem.project.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponseDTO {

    private Long id;

    private String username;

    private String role;

}
