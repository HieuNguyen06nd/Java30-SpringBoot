package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.dto.request.LoginRequest;
import com.hieunguyen.ClinicManagement.dto.request.RegisterRequest;
import com.hieunguyen.ClinicManagement.dto.response.LoginResponse;
import com.hieunguyen.ClinicManagement.entity.Role;
import com.hieunguyen.ClinicManagement.entity.User;
import com.hieunguyen.ClinicManagement.repository.RoleRepository;
import com.hieunguyen.ClinicManagement.repository.UserRepository;
import com.hieunguyen.ClinicManagement.service.JwtService;
import com.hieunguyen.ClinicManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        List<Role> userRoles = request.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .toList();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRoles(userRoles);

        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return response;
    }
}
