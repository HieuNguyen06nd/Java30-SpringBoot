package com.hieunguyen.buoi3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int status;       // HTTP status code
    private String message;   // Success / error message
    private String code;      // Optional error code (nullable if success)

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;           // Payload (nullable if error)

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Thành công nhé", null, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, null, data);
    }


}
