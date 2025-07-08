package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.entity.Role;
import com.hieunguyen.ClinicManagement.exception.BusinessException;
import com.hieunguyen.ClinicManagement.exception.ErrorCode;
import com.hieunguyen.ClinicManagement.repository.RoleRepository;
import com.hieunguyen.ClinicManagement.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        if (role.getName() == null || role.getName().isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_ROLE_NAME);
        }

        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new BusinessException(ErrorCode.ROLE_ALREADY_EXISTS);
        }

        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND, "Role ID not found: " + id));
    }
}
