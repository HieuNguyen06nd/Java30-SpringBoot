package com.hieunguyen.ClinicManagement.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePatientRequest {
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String address;
    private Long userId; // optional: gắn nếu có tài khoản
}
