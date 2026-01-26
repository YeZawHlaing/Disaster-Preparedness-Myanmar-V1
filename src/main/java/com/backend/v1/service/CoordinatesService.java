package com.backend.v1.service;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.CoordinatesRequestDto;
import com.backend.v1.dto.response.CoordinatesResponseDto;

import java.util.List;

public interface CoordinatesService {
    public List<CoordinatesRequestDto> getAllCoordinates();

}
