package com.backend.v1.service;

import com.backend.v1.exceptions.ExpiredOtpCodeException;
import com.backend.v1.exceptions.InvalidOtpCodeException;
import com.backend.v1.model.Otp;
import jakarta.transaction.Transactional;

public interface OtpService{
    boolean validateOtpCode(String email, int otpCode) throws InvalidOtpCodeException, ExpiredOtpCodeException, Exception;

    @Transactional
    Otp associateOtpWithEmail(String email, int otpCode);

}
