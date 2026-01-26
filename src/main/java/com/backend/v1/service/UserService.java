package com.backend.v1.service;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.response.PaginatedApiResponse;
import com.backend.v1.dto.request.GetAllUserRequest;
import com.backend.v1.dto.request.UserCreatedRequestDto;
import com.backend.v1.dto.response.UserResponseDto;
import com.backend.v1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public ApiResponse createUser(UserCreatedRequestDto request);
    PaginatedApiResponse<UserResponseDto> getAllUsers(GetAllUserRequest getAllUserRequest);
    Optional<User> getUserByEmail(String email);
}
