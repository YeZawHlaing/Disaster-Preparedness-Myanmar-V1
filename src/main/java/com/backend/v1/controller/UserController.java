package com.backend.v1.controller;


import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.utils.ResponseUtils;
import com.backend.v1.dto.request.LoginRequestDto;
import com.backend.v1.dto.request.UserCreatedRequestDto;
import com.backend.v1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backend/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody final UserCreatedRequestDto userRequest, final HttpServletRequest request) {
            final ApiResponse response = this.userService.createUser(userRequest);
            return ResponseUtils.buildResponse(request, response);
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody final LoginRequestDto requestDto){
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        String jwt = null;

        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(email, password);
        var authenticationResponse =  authenticationManager.authenticate(authentication);

        if(authenticationResponse != null && authenticationResponse.isAuthenticated()){

        }

    }




}