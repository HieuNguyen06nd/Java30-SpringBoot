package com.hieunguyen.authmanage.controller;

import com.hieunguyen.authmanage.dto.request.LoginRequest;
import com.hieunguyen.authmanage.dto.request.RegisterRequest;
import com.hieunguyen.authmanage.dto.response.AuthResponse;
import com.hieunguyen.authmanage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

}
