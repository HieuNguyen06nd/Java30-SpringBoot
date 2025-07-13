package com.hieunguyen.authmanage.dto.request;

import com.hieunguyen.authmanage.utils.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}

