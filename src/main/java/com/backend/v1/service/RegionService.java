package com.backend.v1.service;


import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.RegionRequestDto;
import com.backend.v1.dto.response.RegionResponseDto;

import java.util.List;

public interface RegionService {

    public ApiResponse create(RegionRequestDto dot);

    public ApiResponse getAll();

    public ApiResponse getById(Long id);

    public ApiResponse update(Long id,RegionRequestDto dto);

    public ApiResponse  delete(Long id);

}
