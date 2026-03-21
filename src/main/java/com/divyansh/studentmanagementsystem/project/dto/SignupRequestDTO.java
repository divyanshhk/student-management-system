package com.divyansh.studentmanagementsystem.project.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDTO {

    private String username;

    private String Password;

}
