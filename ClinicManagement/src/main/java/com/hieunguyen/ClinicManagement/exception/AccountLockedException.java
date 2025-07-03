package com.hieunguyen.ClinicManagement.exception;

public class AccountLockedException extends RuntimeException {
    private final String errorCode;
    private final int incorrectPasswordTimes;
    private final int incorrectCaptchaTimes;
    private final Integer lockTimeMinutes;

    public AccountLockedException(String message, String errorCode,
                                  int incorrectPasswordTimes, int incorrectCaptchaTimes,
                                  Integer lockTimeMinutes) {
        super(message);
        this.errorCode = errorCode;
        this.incorrectPasswordTimes = incorrectPasswordTimes;
        this.incorrectCaptchaTimes = incorrectCaptchaTimes;
        this.lockTimeMinutes = lockTimeMinutes;
    }

    public String getErrorCode() { return errorCode; }
    public int getIncorrectPasswordTimes() { return incorrectPasswordTimes; }
    public int getIncorrectCaptchaTimes() { return incorrectCaptchaTimes; }
    public Integer getLockTimeMinutes() { return lockTimeMinutes; }
}

