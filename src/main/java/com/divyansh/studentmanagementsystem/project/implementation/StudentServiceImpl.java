package com.divyansh.studentmanagementsystem.project.implementation;

import com.divyansh.studentmanagementsystem.project.dto.StudentRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.StudentResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.Student;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import com.divyansh.studentmanagementsystem.project.repository.StudentRepository;
import com.divyansh.studentmanagementsystem.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    @Override
    public Page<StudentResponseDTO> getAllStudent(Pageable pageable) {
//        Sort sort = direction.equalsIgnoreCase("desc") ?
//                Sort.by(sortBy).descending():
//                Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Student> students = studentRepository.findAll(pageable);

        return students.map(student -> modelMapper.map(student, StudentResponseDTO.class));

    }

    @Override
    public StudentResponseDTO updateStudent(long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        modelMapper.map(studentRequestDTO, student);
        Student updatedStudent = studentRepository.save(student);

        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }

    @Override
    public void deleteStudentById(long id) {
        if(!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentResponseDTO> searchStudentByName(String title, Pageable pageable) {
        Page<Student> students = studentRepository.findByNameContaining(title, pageable);
        return students.map (student -> modelMapper.map(student, StudentResponseDTO.class));
    }


}
