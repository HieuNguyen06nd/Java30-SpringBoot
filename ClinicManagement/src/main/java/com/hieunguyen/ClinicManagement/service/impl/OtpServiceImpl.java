package com.hieunguyen.ClinicManagement.service.impl;

import com.hieunguyen.ClinicManagement.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final StringRedisTemplate redisTemplate;
    private final JavaMailSender mailSender;
    private final Random random = new Random();
    private static final long EXPIRE_MINUTES = 3;

    @Override
    public void sendOtp(String email) {
        String otp = String.format("%06d", random.nextInt(999999));

        // 2. Lưu vào Redis với key: otp:{email}, hết hạn sau 3 phút
        redisTemplate.opsForValue().set("otp:" + email, otp, EXPIRE_MINUTES, TimeUnit.MINUTES);

        // 3. Gửi email thật
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã OTP xác thực");
        message.setText("Mã OTP của bạn là: " + otp + "\nHiệu lực trong " + EXPIRE_MINUTES + " phút.");

        mailSender.send(message);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get("otp:" + email);
        return storedOtp != null && storedOtp.equals(otp);
    }
}
