package com.backend.v1.exceptions;

public class ExpiredOtpCodeException extends RuntimeException {
    public ExpiredOtpCodeException(String message) {
        super(message);
    }
}
