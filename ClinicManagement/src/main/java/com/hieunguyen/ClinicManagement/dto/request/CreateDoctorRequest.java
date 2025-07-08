package com.hieunguyen.ClinicManagement.dto.request;

import lombok.Data;

@Data
public class CreateDoctorRequest {
    private String name;
    private String department;
    private Boolean available;

    // Thông tin tài khoản
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
