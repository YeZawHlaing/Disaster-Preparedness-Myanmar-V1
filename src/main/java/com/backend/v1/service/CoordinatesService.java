package com.backend.v1.service;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.response.CoordinatesResponseDto;

public interface CoordinatesService {
    public ApiResponse getAllCoordinates();
}
