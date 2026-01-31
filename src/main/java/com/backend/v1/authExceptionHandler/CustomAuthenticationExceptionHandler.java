package com.backend.v1.authExceptionHandler;

import com.backend.v1.common.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomAuthenticationExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        var apiResponse = ApiResponse.builder()
                .message(authException.getMessage())
                .success(0)
                .build();
        response.setContentType("application/json;charset=utf-8");
        var jsonResponse = apiResponseToJson(apiResponse);
        response.getWriter().write(jsonResponse);
    }

    private String apiResponseToJson(ApiResponse apiResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(apiResponse);
    }

}
