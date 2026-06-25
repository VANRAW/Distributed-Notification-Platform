package com.swarnav.notification_platform.controller;

import com.swarnav.notification_platform.dto.AuthResponse;
import com.swarnav.notification_platform.dto.LoginRequest;
import com.swarnav.notification_platform.dto.RegisterRequest;
import com.swarnav.notification_platform.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication APIs")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user")
@PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
public AuthResponse login(@RequestBody LoginRequest request){
    return authService.login(request);
}

}
