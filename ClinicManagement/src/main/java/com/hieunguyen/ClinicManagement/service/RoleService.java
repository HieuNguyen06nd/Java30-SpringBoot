package com.hieunguyen.ClinicManagement.service;

import javax.management.relation.Role;

public interface RoleService {

    Role createRole(Role role);

    Role getRoleById(Long id);
}
