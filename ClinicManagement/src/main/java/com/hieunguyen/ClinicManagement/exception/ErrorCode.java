package com.hieunguyen.ClinicManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // ===== AUTHENTICATION =====
    AUTH_FAILED("AUTH_001", "Sai tên đăng nhập hoặc mật khẩu"),
    ACCOUNT_LOCKED("AUTH_002", "Tài khoản đã bị khóa"),
    PASSWORD_EXPIRED("AUTH_003", "Mật khẩu đã hết hạn"),
    REQUIRE_PASSWORD_CHANGE("AUTH_004", "Yêu cầu đổi mật khẩu"),

    // ===== VALIDATION =====
    VALIDATION_ERROR("VAL_001", "Dữ liệu không hợp lệ"),
    PASSWORD_TOO_SHORT("VAL_002", "Độ dài mật khẩu quá ngắn"),
    PASSWORD_TOO_WEAK("VAL_003", "Mật khẩu không đủ mạnh"),

    // ===== CAPTCHA =====
    CAPTCHA_INVALID("CAP_001", "Captcha không hợp lệ"),
    CAPTCHA_REQUIRED("CAP_002", "Vui lòng nhập Captcha"),

    // ===== SYSTEM =====
    INTERNAL_ERROR("SYS_001", "Lỗi hệ thống. Vui lòng thử lại sau"),

    // ===== BUSINESS =====
    USER_NOT_FOUND("BUS_001", "Không tìm thấy người dùng"),
    CONFIG_NOT_FOUND("BUS_002", "Không tìm thấy cấu hình"),
    DUPLICATE_POLICY("BUS_003", "Chính sách đã tồn tại"),

    USERNAME_ALREADY_EXISTS("USR_001", "Tên đăng nhập đã tồn tại"),
    ROLE_NOT_FOUND("ROL_001", "Không tìm thấy quyền"),
    INVALID_CREDENTIALS("AUTH_001", "Sai tên đăng nhập hoặc mật khẩu");

    private final String code;
    private final String message;

}
