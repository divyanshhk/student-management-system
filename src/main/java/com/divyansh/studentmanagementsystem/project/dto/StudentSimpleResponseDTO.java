package com.divyansh.studentmanagementsystem.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSimpleResponseDTO {

    private long id;

    private String name;

    private int age;

    private long phone;

    private String email;
}
