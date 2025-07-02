package com.hieunguyen.ClinicManagement.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterRequest {
    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private List<String> roles;
}
