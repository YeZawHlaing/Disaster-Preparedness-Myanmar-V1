package com.backend.v1.service;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.response.PaginatedApiResponse;
import com.backend.v1.dto.request.GetAllUserRequest;
import com.backend.v1.dto.request.UserCreatedRequestDto;
import com.backend.v1.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    public ApiResponse createUser(UserCreatedRequestDto request);
    PaginatedApiResponse<UserResponseDto> getAllUsers(GetAllUserRequest getAllUserRequest);

}
