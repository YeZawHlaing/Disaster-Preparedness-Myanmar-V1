package com.backend.v1.common.midddleware;

import com.backend.v1.common.constant.JwtConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtGenerationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null ) {
            Environment environment = getEnvironment();

            if(environment != null ) {
                String secret =  environment.getProperty(JwtConstants.JWT_SECRET_KEY, JwtConstants.JWT_SECRET_KEY_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                String jwtToken = Jwts.builder()
                                        .setIssuer("DPM-Organization")
                                        .setSubject("JWT-TOKEN")
                                        .claim("email", authentication.getName())
                                        .claim("authorities", authentication.getAuthorities()
                                                .stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.joining(",")))
                                        .setIssuedAt(new Date())
                                        .setExpiration(new Date(new Date().getTime() + 8000L))
                                        .signWith(secretKey)
                                        .compact();

                response.setHeader(JwtConstants.JWT_SECRET_KEY, jwtToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return false;
    }
}
