package com.hieunguyen.ClinicManagement.service;

import com.hieunguyen.ClinicManagement.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    Role getRoleById(Long id);
}
