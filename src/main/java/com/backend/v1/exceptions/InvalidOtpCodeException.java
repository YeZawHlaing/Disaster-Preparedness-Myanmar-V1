package com.backend.v1.exceptions;

public class InvalidOtpCodeException extends RuntimeException {
    public InvalidOtpCodeException(String message) {
        super(message);
    }
}
