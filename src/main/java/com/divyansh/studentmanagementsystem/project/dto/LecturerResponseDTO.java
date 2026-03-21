package com.divyansh.studentmanagementsystem.project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LecturerResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String department;

}
