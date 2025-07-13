package com.hieunguyen.authmanage.service;

import com.hieunguyen.authmanage.dto.request.LoginRequest;
import com.hieunguyen.authmanage.dto.request.RegisterRequest;
import com.hieunguyen.authmanage.dto.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
