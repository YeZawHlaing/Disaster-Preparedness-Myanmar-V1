package com.backend.v1.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    @Value("${jwt.secret.key}")
    private static String jwtSecretKey;

    public static String generate(Long expiresAfterInMinutes, Map<String, String> payload) throws IllegalArgumentException, JwtException {

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));

        if (secretKey == null) {
            throw new IllegalStateException("JWT secret key not found in environment or properties");
        }

        var jwtToken = Jwts.builder()
                                .setIssuer("DPM-Organization")
                                .setSubject("JWT Token")
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(new Date().getTime()  + (expiresAfterInMinutes * 60L)))
                                .setClaims(payload)
                                .signWith(secretKey)
                                .compact();

        return jwtToken;
    }

    public static Claims validate(String jwtToken) throws JwtException{

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
        var claims = Jwts.parserBuilder()
                                .setSigningKey(secretKey)
                                .build()
                                .parseClaimsJwt(jwtToken)
                                .getBody();
        return claims;
    }

}

















