package com.hieunguyen.ClinicManagement.service;

import com.hieunguyen.ClinicManagement.dto.request.LoginRequest;
import com.hieunguyen.ClinicManagement.dto.request.RegisterRequest;
import com.hieunguyen.ClinicManagement.dto.response.LoginResponse;
import com.hieunguyen.ClinicManagement.entity.User;

public interface UserService {
    User register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    User getCurrentUser(String token);

}
