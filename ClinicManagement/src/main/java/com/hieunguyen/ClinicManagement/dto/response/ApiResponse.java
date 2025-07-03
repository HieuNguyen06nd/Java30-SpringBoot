package com.hieunguyen.ClinicManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int status;
    private String error;
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
    private T data;
}

