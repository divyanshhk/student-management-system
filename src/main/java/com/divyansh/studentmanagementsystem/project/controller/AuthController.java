package com.divyansh.studentmanagementsystem.project.controller;

import com.divyansh.studentmanagementsystem.project.dto.SignupRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.SignupResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.LoginResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.LoginRequestDTO;
import com.divyansh.studentmanagementsystem.project.response.ApiResponse;
import com.divyansh.studentmanagementsystem.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponseDTO>> signup(@RequestBody SignupRequestDTO signupRequestDTO) {

        SignupResponseDTO signup = authService.signup(signupRequestDTO);

        ApiResponse<SignupResponseDTO> response = new ApiResponse<>(true, "user registered successfully", signup);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO login =authService.login(loginRequestDTO);

        ApiResponse<LoginResponseDTO> response = new ApiResponse<>(true, "login successful", login);

        return ResponseEntity.ok(response);


    }
}
