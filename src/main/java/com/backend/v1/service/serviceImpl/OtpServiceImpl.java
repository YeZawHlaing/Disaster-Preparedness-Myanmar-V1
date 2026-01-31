package com.backend.v1.service.serviceImpl;

import com.backend.v1.exceptions.ExpiredOtpCodeException;
import com.backend.v1.exceptions.InvalidOtpCodeException;
import com.backend.v1.model.Otp;
import com.backend.v1.repository.OtpRepository;
import com.backend.v1.service.OtpService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.backend.v1.common.utils.OtpUtils.checkIfExpired;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    @Override
    public boolean validateOtpCode(String email, int otpCode) throws InvalidOtpCodeException, ExpiredOtpCodeException, Exception{
        var otp = otpRepository.findByEmailAndCode(email, otpCode).orElseThrow(()->{
            throw new InvalidOtpCodeException("Otp code is invalid");
        });

        if(!(otpCode == otp.getCode())){
            throw new InvalidOtpCodeException("Wrong Otp code");
        }else {
            if(checkIfExpired(otp)){
                throw new ExpiredOtpCodeException("Otp code is expired");
            }else {
                otpRepository.delete(otp);
                return true;
            }
        }
    }

    @Override
    public Otp associateOtpWithEmail(String email, int otpCode){

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime issuedTime = now;
        LocalDateTime expiredTime = now.plusSeconds(180); // 3 minutes

        Otp otp = Otp.builder()
                .code(otpCode)
                .email(email)
                .issuedAt(issuedTime)
                .expiredAt(expiredTime)
                .build();

        otpRepository.save(otp);

        return otp;

    }






}
