package com.hieunguyen.ClinicManagement.dto.response;

import com.hieunguyen.ClinicManagement.entity.Role;
import com.hieunguyen.ClinicManagement.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<String> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
