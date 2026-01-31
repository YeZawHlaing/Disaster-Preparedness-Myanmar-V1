package com.backend.v1.controller;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.utils.JwtUtils;
import com.backend.v1.dto.response.LoginSuccessResponseDto;
import com.backend.v1.dto.request.LoginRequestDto;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse> handleAllJwtException(JwtException jwtException){

        var response = ApiResponse.builder()
                .message(jwtException.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequestDto loginRequestDto){

        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        var authentication = new UsernamePasswordAuthenticationToken(email, password);

        String jwtToken = null;

        var authenticationResponse =  authenticationManager.authenticate(authentication);

        if(authenticationResponse.isAuthenticated()) {

            String loggedEmail = authenticationResponse.getName();
            var rolesAndAuthorities = authenticationResponse.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));


            jwtToken = JwtUtils.generateJwtToken(email, rolesAndAuthorities);


            LoginSuccessResponseDto responseDto = new LoginSuccessResponseDto(loggedEmail, jwtToken);

            return ApiResponse.builder().
                    data(responseDto)
                    .code(HttpStatus.OK.value())
                    .message("Login: Success")
                    .success(1)
                    .build();
        }else {
            throw new BadCredentialsException("Username or password is wrong");
        }

    }
}
