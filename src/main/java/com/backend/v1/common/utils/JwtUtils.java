package com.backend.v1.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static String secretKey = Encoders.BASE64.encode("sofjksdjfkdsajfkdsafjkdsafjlkdsafjlkdsafjlkdsafjlksajfkdsjfasjdflskdafjlksdjflkd".getBytes(StandardCharsets.UTF_8));

    public static String generateJwtToken(String email, String role) throws JwtException {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", email);
        claims.put("role", role);

        var key = getKey();

        var token = Jwts.builder()
                .claims()
                .add(claims)
                .subject("Jwt token generation")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();
        return token;
    }

    public static SecretKey getKey() {
        var keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Claims validateJwtToken(String token) throws JwtException{
        var key = getKey();
        var claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }
}
