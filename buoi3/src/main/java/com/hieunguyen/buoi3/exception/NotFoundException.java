package com.hieunguyen.buoi3.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message, ErrorCode.STUDENT_NOT_FOUND);
    }
}
