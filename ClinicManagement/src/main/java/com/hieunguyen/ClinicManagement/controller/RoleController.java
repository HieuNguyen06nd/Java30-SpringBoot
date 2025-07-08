package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.entity.Role;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody Role role) {
        if (role.getName() == null || role.getName().isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_ROLE_NAME);
        }

        Role createdRole = roleService.createRole(role);

        ApiResponse<Role> response = ApiResponse.<Role>builder()
                .status(HttpStatus.CREATED.value())
                .message("Role created successfully")
                .timestamp(LocalDateTime.now())
                .data(createdRole)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id); // nếu không có sẽ tự ném BusinessException bên Service

        ApiResponse<Role> response = ApiResponse.<Role>builder()
                .status(HttpStatus.OK.value())
                .message("Role fetched successfully")
                .timestamp(LocalDateTime.now())
                .data(role)
                .build();

        return ResponseEntity.ok(response);
    }
}

