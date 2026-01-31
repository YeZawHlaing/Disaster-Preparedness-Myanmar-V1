package com.backend.v1.exceptions;

public class EmailServiceErrorException extends RuntimeException {
    public EmailServiceErrorException(String message) {
        super(message);
    }
}
