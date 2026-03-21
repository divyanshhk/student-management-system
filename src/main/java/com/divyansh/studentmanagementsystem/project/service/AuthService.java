package com.divyansh.studentmanagementsystem.project.service;

import com.divyansh.studentmanagementsystem.project.dto.LoginResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.LoginRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.SignupRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.SignupResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
}
