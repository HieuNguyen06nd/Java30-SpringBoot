package com.hieunguyen.authmanage.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String username;
    private String role;
    private String token;
}

