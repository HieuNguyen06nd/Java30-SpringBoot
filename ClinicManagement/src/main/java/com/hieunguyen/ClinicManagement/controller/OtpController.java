package com.hieunguyen.ClinicManagement.controller;

import com.hieunguyen.ClinicManagement.dto.response.ApiResponse;
import com.hieunguyen.ClinicManagement.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestParam String key) {
        otpService.sendOtp(key);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("OTP sent to " + key)
                        .build()
        );
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String key, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(key, otp);
        return isValid
                ? ResponseEntity.ok(ApiResponse.builder().status(200).message("OTP verified").build())
                : ResponseEntity.badRequest().body(ApiResponse.builder().status(400).message("Invalid OTP").build());
    }
}
