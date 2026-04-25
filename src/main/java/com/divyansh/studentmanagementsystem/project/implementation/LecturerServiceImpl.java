package com.divyansh.studentmanagementsystem.project.implementation;

import com.divyansh.studentmanagementsystem.project.dto.LecturerRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.LecturerResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.Lecturer;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import com.divyansh.studentmanagementsystem.project.repository.LecturerRepository;
import com.divyansh.studentmanagementsystem.project.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final ModelMapper modelMapper;


    @Override
    public LecturerResponseDTO createLecturer(LecturerRequestDTO lecturerRequestDTO) {
        Lecturer lecturer = modelMapper.map(lecturerRequestDTO, Lecturer.class);
        Lecturer savedLecturer = lecturerRepository.save(lecturer);
        return modelMapper.map(savedLecturer, LecturerResponseDTO.class);
    }

    @Override
    public LecturerResponseDTO getLecturerById(Long lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer with id: " + lecturerId + " not found"));

        return modelMapper.map(lecturer, LecturerResponseDTO.class);

    }

    @Override
    public Page<LecturerResponseDTO> getAllLecturer(Pageable pageable) {

        Page<Lecturer> lecturers = lecturerRepository.findAll(pageable);

        return lecturers
                .map(lecturer -> modelMapper.map(lecturer, LecturerResponseDTO.class));

    }

    @Override
    public LecturerResponseDTO updateLecturer(Long lecturerId, LecturerRequestDTO lecturerRequestDTO) {
    Lecturer lecturer = lecturerRepository.findById(lecturerId).orElseThrow(() -> new ResourceNotFoundException("Lecturer: " + lecturerId + " not found"));
    modelMapper.map(lecturerRequestDTO,  lecturer);
    Lecturer savedLecturer = lecturerRepository.save(lecturer);
    return modelMapper.map(savedLecturer, LecturerResponseDTO.class);
    }

    @Override
    public void deleteLecturer(Long lecturerId) {

        if (!lecturerRepository.existsById(lecturerId)) {
            throw new ResourceNotFoundException("Lecturer with id: " + lecturerId + " not found");
        }
        lecturerRepository.deleteById(lecturerId);
    }


}
