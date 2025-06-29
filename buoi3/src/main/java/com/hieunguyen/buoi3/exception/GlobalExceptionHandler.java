package com.hieunguyen.buoi3.exception;

import com.hieunguyen.buoi3.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                400,
                ex.getMessage(),
                ex.getErrorCode().name(),
                null
        );
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleSystemException(Exception ex) {
        ex.printStackTrace(); // in log server
        ApiResponse<Object> response = new ApiResponse<>(
                500,
                "Lỗi hệ thống",
                ErrorCode.SYSTEM_ERROR.name(),
                null
        );
        return ResponseEntity.status(500).body(response);
    }
}
