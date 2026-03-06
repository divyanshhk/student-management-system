package com.divyansh.studentmanagementsystem.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class subjects {

    @Id
    private Long subjectCode;

    private String subjectUnit;

    private String subjectDesc;
}
