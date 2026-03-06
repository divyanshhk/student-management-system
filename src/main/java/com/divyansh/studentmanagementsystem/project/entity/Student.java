package com.divyansh.studentmanagementsystem.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private Long phone;

    private String email;

    //private String department;
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}
