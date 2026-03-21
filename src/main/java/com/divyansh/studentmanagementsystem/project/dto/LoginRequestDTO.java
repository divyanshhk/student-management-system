package com.divyansh.studentmanagementsystem.project.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {

    private String username;

    private String password;
}
