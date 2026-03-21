package com.divyansh.studentmanagementsystem.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false )
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false )
    private Course course;

    @CreationTimestamp
    private LocalDateTime enrolledAt;
}
