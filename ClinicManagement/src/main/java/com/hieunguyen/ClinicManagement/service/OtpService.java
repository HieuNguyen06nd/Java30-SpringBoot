package com.hieunguyen.ClinicManagement.service;

public interface OtpService {
    void sendOtp(String phoneOrEmail); // Gửi OTP (qua Redis)
    boolean verifyOtp(String phoneOrEmail, String otp); // Xác thực OTP
}


