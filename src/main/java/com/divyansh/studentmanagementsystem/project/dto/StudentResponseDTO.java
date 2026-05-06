package com.divyansh.studentmanagementsystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class StudentResponseDTO {

    private long id;

    private String name;

    private int age;

    private long phone;

    private String email;

    List<CourseResponseDTO> courses;


    // 1. MUST HAVE: No-args constructor for Jackson
    public StudentResponseDTO() {
    }

    // 2. MUST HAVE: Manual Getters (Jackson uses these for JSON)
    public long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public long getPhone() { return phone; }
    public String getEmail() { return email; }
    public List<CourseResponseDTO> getCourses() { return courses; }

    // 3. Setters (for your Service/Mapper to fill the data)
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPhone(long phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setCourses(List<CourseResponseDTO> courses) { this.courses = courses; }
}
