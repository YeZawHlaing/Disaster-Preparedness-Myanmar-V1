package com.backend.v1.controller;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.utils.EmailDetails;
import com.backend.v1.common.utils.OtpUtils;
import com.backend.v1.common.utils.ResponseUtils;
import com.backend.v1.dto.OtpRequestDto;
import com.backend.v1.dto.OtpValidateRequestDto;
import com.backend.v1.exceptions.ExpiredOtpCodeException;
import com.backend.v1.exceptions.InvalidOtpCodeException;
import com.backend.v1.service.EmailService;
import com.backend.v1.service.OtpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;
    private final EmailService emailService;

    @ExceptionHandler(InvalidOtpCodeException.class)
    public ResponseEntity<ApiResponse> handleInvalidOtpCodeException(HttpServletRequest request, InvalidOtpCodeException e){

        var response = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseUtils.buildResponse(request, response);
    }

    @ExceptionHandler(ExpiredOtpCodeException.class)
    public ResponseEntity<ApiResponse> handleExpiredOtpCodeException(HttpServletRequest request, ExpiredOtpCodeException e){

        var response = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseUtils.buildResponse(request, response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllException(HttpServletRequest request, Exception e){
        var response = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseUtils.buildResponse(request, response);
    }



    @PostMapping("/generate")
    public ApiResponse getOtpCode(@RequestBody OtpRequestDto otpRequestDto){
        int otpCode = OtpUtils.generateOtp();
        otpService.associateOtpWithEmail(otpRequestDto.getEmail(), otpCode);

        var emailDetails = new EmailDetails();
        emailDetails.setRecipient(otpRequestDto.getEmail());
        emailDetails.setSubject("OTP test");
        emailDetails.setMsgBody(String.valueOf(otpCode));

        emailService.sendSimpleMail(emailDetails);
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Otp code is sent successfully")
                .build();
    }

    @PostMapping("/validate")
    public ApiResponse validateOtp(@RequestBody OtpValidateRequestDto otpValidateRequestDto){
        try {
            otpService.validateOtpCode(otpValidateRequestDto.getEmail(), Integer.parseInt(otpValidateRequestDto.getOtpCode()));
            return ApiResponse.builder()
                    .code(HttpStatus.OK.value())
                    .message("Otp code : right")
                    .build();


        } catch (InvalidOtpCodeException e) {
            throw new RuntimeException(e);
        }
        catch (ExpiredOtpCodeException e){
            throw new RuntimeException(e);
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
