package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.request.LoginRequest;
import com.hieunguyen.ClinicManagement.dto.request.RegisterRequest;
import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.dto.response.LoginResponse;
import com.hieunguyen.ClinicManagement.dto.response.UserResponse;
import com.hieunguyen.ClinicManagement.entity.User;
import com.hieunguyen.ClinicManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request); // service chỉ xử lý & trả entity

        UserResponse dto = new UserResponse(user); // convert ở controller

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<UserResponse>builder()
                        .status(201)
                        .message("Đăng ký thành công")
                        .timestamp(LocalDateTime.now())
                        .data(dto)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);

        ApiResponse<LoginResponse> apiResponse = ApiResponse.<LoginResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Đăng nhập thành công")
                .timestamp(LocalDateTime.now())
                .data(response)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getCurrentUser(@RequestHeader("Authorization") String token) {
        User user = userService.getCurrentUser(token);

        ApiResponse<User> response = ApiResponse.<User>builder()
                .status(HttpStatus.OK.value())
                .message("Lấy thông tin người dùng thành công")
                .timestamp(LocalDateTime.now())
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }

}
