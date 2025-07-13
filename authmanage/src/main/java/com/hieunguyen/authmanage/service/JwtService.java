package com.hieunguyen.authmanage.service;

import com.hieunguyen.authmanage.entity.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {
    String generateToken(User user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
