package com.swarnav.notification_platform.service;

import com.swarnav.notification_platform.dto.LoginRequest;
import com.swarnav.notification_platform.dto.RegisterRequest;
import com.swarnav.notification_platform.entity.User;
import com.swarnav.notification_platform.exception.BadRequestException;
import com.swarnav.notification_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.swarnav.notification_platform.dto.AuthResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }
    
    public AuthResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() ->
                    new BadRequestException("User not found"));

    boolean matches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    );

    if(!matches){
        throw new BadRequestException("Invalid credentials");
    }

    String token = jwtService.generateToken(user.getEmail());

    return new AuthResponse(token);
    }
}
