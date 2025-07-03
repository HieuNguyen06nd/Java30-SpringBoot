package com.hieunguyen.ClinicManagement.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;       // JWT token nếu dùng bảo mật
    private String username;
    private String email;
    private String role;
}
