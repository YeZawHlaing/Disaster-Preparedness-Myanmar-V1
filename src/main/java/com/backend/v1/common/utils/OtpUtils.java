package com.backend.v1.common.utils;

import com.backend.v1.model.Otp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OtpUtils {

    public static int generateOtp(){
        int randomNumber = (int) (Math.random() * 900000) + 100000;
        return randomNumber;
    }

    public static boolean checkIfExpired(Otp otp){
        LocalDateTime now = LocalDateTime.now();
        if(!now.isBefore(otp.getExpiredAt())){
            return true;
        }else {
            return false;
        }
    }

}
