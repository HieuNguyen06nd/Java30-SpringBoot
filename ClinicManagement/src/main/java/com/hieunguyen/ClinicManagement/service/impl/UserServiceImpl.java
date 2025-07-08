package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.dto.request.LoginRequest;
import com.hieunguyen.ClinicManagement.dto.request.RegisterRequest;
import com.hieunguyen.ClinicManagement.dto.response.LoginResponse;
import com.hieunguyen.ClinicManagement.entity.Role;
import com.hieunguyen.ClinicManagement.entity.User;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.repository.RoleRepository;
import com.hieunguyen.ClinicManagement.repository.UserRepository;
import com.hieunguyen.ClinicManagement.service.JwtService;
import com.hieunguyen.ClinicManagement.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new BusinessException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
        }

        // 🔹 Lấy Role từ DB
        List<Role> roles = request.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND, "Role: " + roleName)))
                .collect(Collectors.toList());

        // 🔹 Tạo user & gán role trước khi lưu
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRoles(new java.util.ArrayList<>(roles)); // ✅ Gán mutable list

        return userRepository.save(user);
    }




    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String token = jwtService.generateToken(user.getUsername());

        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User getCurrentUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String username = jwtService.extractUsername(token);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @PostConstruct
    public void initDefaultAdmin() {
        if (!userRepository.existsByUsername("admin")) {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ADMIN");
                        return roleRepository.save(r);
                    });

            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("Admin@123"));
            user.setEmail("admin@clinic.com");
            user.setPhoneNumber("0123456789");
            // ✅ Sửa List.of(...) thành mutable list
            user.setRoles(new ArrayList<>(Collections.singletonList(adminRole)));

            userRepository.save(user);
            System.out.println("✅ Admin mặc định đã được tạo: admin / Admin@123");
        }
    }
}
