package com.divyansh.studentmanagementsystem.project.implementation;


import com.divyansh.studentmanagementsystem.project.dto.LoginResponseDTO;
import com.divyansh.studentmanagementsystem.project.dto.LoginRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.SignupRequestDTO;
import com.divyansh.studentmanagementsystem.project.dto.SignupResponseDTO;
import com.divyansh.studentmanagementsystem.project.entity.User;
import com.divyansh.studentmanagementsystem.project.enumerate.Role;
import com.divyansh.studentmanagementsystem.project.repository.UserRepository;
import com.divyansh.studentmanagementsystem.project.security.JwtUtil;
import com.divyansh.studentmanagementsystem.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {

        User existingUser = userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if (existingUser != null) throw new IllegalArgumentException("user already exists");

        User user = modelMapper.map(signupRequestDTO, User.class);

        // Password Encoder
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        
        // Role Handling 
//        user.setRole(
//                signupRequestDTO.getRole() == null
//                ? Role.USER
//                : Role.valueOf(signupRequestDTO.getRole())
//        );
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, SignupResponseDTO.class);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        System.out.println("LOGIN SERVICE START");

        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("User found: " + user.getUsername());

        // ✅ CORRECT password check
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            System.out.println("Password match: false");
            throw new RuntimeException("Invalid password");
        }

        System.out.println("Password match: true");

        String token = jwtUtil.generateToken(user.getUsername());

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

}
